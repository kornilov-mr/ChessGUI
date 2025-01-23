package org.example.UIComponents.components.auth;

/**
 * Auth states for State Pattern
 */
public enum LoginState {
    Register(new RegisterInstruction()),
    Login(new LoginInstruction()),;
    private final AuthInstruction authInstruction;

    LoginState(AuthInstruction authInstruction) {
        this.authInstruction = authInstruction;
    }

    public static LoginState switchToNext(LoginState state) {
        return state.equals(Login) ? Register : Login;
    }

    public AuthInstruction getAuthInstruction() {
        return authInstruction;
    }
}
