package org.example.serverConnection;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

import com.fasterxml.jackson.databind.ObjectMapper;
import gameControl.gameController.AbstractGameController;
import gameControl.gameController.GameBuilder;
import gameControl.gameController.OnlineGameController;
import gameControl.timer.TimerEnum;
import logic.BoardController;
import logic.chessPieces.PieceColor;
import logic.pieceDisposition.BoardDispositionSetting;
import org.example.UIComponents.dialogs.SimpleDialogFactory;
import org.example.account.AppContext;
import org.example.serverConnection.dto.LoginUserDto;
import org.example.serverConnection.dto.RegisterUserDto;
import org.example.serverConnection.dto.TableDto;
import org.example.serverConnection.responses.AccountDataResponse;
import org.example.serverConnection.responses.LoginResponse;
import org.example.serverConnection.responses.TableResponse;
import org.example.utils.logical.DotEnv;


/**
 * class with common used Http requests
 */
public class CommonHttpBuilder {

    private static final String SERVER_ROOT = DotEnv.getValue("SERVER_ROOT");


    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * send http request to login endpoint
     * without using auth
     * @param username user's nickname
     * @param password user's password
     * @return JWTToken which is then used in Account to update user data
     */
    public static String login(String username, String password) throws IOException, InterruptedException, URISyntaxException {
        LoginUserDto loginUserDto = new LoginUserDto(username, password);

        String bodyAsString = objectMapper.writeValueAsString(loginUserDto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://"+ SERVER_ROOT + DotEnv.getValue("SIGNUP_ENDPOINT_PATH")))
                .POST(HttpRequest.BodyPublishers.ofString(bodyAsString))
                .header("Content-Type", "application/json")
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        LoginResponse loginResponse = objectMapper.readValue(response.body(), LoginResponse.class);
        if(response.statusCode() != 200) throw new WrongHttpCode(response.statusCode(),"Code isn't 200");
        return loginResponse.getToken();
    }
    /**
     * send http request to signup endpoint
     * without using auth
     * @param username user's nickname
     * @param password user's password
     */
    public static void register(String username, String password) throws IOException, InterruptedException, URISyntaxException {
        RegisterUserDto registerUserDto = new RegisterUserDto(username, password);

        String bodyAsString = objectMapper.writeValueAsString(registerUserDto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://"+SERVER_ROOT+DotEnv.getValue("LOGIN_ENDPOINT_PATH")))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(bodyAsString))
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200) throw new RuntimeException("Response code not 200");
        if(Objects.equals("user with this username already exists",response.body()))
            throw new UserWithThisUsernameAlreadyExist();
    }
    /**
     * send http request to create table endpoint
     * using JWTToken for auth
     * @param gameBuilder gameBuilder with configuration of the game
     */
    public static Long postTable(GameBuilder gameBuilder) throws IOException, URISyntaxException, InterruptedException {
        assertThatUserIsLoggedIn();
        TableDto tableDto = new TableDto(gameBuilder.getGameName(),
                gameBuilder.getColorChooser().toString(),
                gameBuilder.getGameTimer().toString(),
                gameBuilder.getTheGameOnPoints(),
                gameBuilder.getBoardController().getBoardAsString());

        String bodyAsString = objectMapper.writeValueAsString(tableDto);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://"+SERVER_ROOT+DotEnv.getValue("CREATE_TABLE_ENDPOINT_PATH")))
                .PUT(HttpRequest.BodyPublishers.ofString(bodyAsString))
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ AppContext.getAccount().getJWTToken())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        if(response.statusCode() != 200) throw new WrongHttpCode(response.statusCode(),"Code isn't 200");
        return  Long.parseLong(response.body());
    }

    /**
     * Asserts that user is logged in,
     * if not shows dialog and throws execution
     */
    public static void assertThatUserIsLoggedIn(){
        if(AppContext.getAccount()==null){
            System.out.println("You are not logged in");
            SimpleDialogFactory.showErrorDialog("You should be logged in to access this function");
            throw new RuntimeException("you should be logged in to access this function");
        }
    }

    /**
     * send http request to fetch account data endpoint
     * using JWTToken for auth
     * @param JWTToken JWTToken for auth
     */
    public static AccountDataResponse fetchAccountData(String JWTToken) throws URISyntaxException, IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://"+SERVER_ROOT+DotEnv.getValue("FETCH_ACCOUNT_DATA_ENDPOINT_PATH")))
                .GET()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ JWTToken)
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() != 200) throw new WrongHttpCode(response.statusCode() ,"Response code not 200");
        return objectMapper.readValue(response.body(), AccountDataResponse.class);
    }

    /**
     * send request to fetch game data endpoint,
     * then builds the game and establish websocket connection
     * @param gameId gameId of game, which you want to connect to
     */
    public static AbstractGameController connectToTheGameWithId(Long gameId) throws URISyntaxException, IOException, InterruptedException {
        assertThatUserIsLoggedIn();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("http://"+SERVER_ROOT+DotEnv.getValue("FETCH_TABLE_DATA_ENDPOINT_PATH")+gameId))
                .GET()
                .header("Content-Type", "application/json")
                .header("Authorization","Bearer "+ AppContext.getAccount().getJWTToken())
                .build();

        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        TableResponse tableResponse = objectMapper.readValue(response.body(), TableResponse.class);
        OnlineGameController onlineGameController = new OnlineGameController(PieceColor.changePieceColor(PieceColor.valueOf(tableResponse.getOwnerColor())),
                new BoardController(new BoardDispositionSetting(tableResponse.getPiecePosition())),
                TimerEnum.getTimerEnumByName(tableResponse.getTimerTypeName()).getTamer(),
                gameId);
        if(response.statusCode() != 200) throw new WrongHttpCode(response.statusCode(),"Code isn't 200");

        return onlineGameController;
    }
}
