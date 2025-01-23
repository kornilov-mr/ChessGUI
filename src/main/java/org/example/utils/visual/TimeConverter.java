package org.example.utils.visual;

public class TimeConverter {
    public static String toNormalRepresentation(Long milliseconds){
        if(milliseconds>=1000*60*60*24){
            if(milliseconds>=1000*60*60*24*2) return milliseconds/1000*60*60*24+" days";
            return "1 day";
        }
        if(milliseconds>=1000*60*60){
            if(milliseconds>=1000*60*60*2) return milliseconds/1000*60*60*24+" hours";
            return "1 hour";
        }
        if(milliseconds>=1000*60){
            return milliseconds/1000/60+":"+(milliseconds/1000)%60;
        }
        if(milliseconds>=1000){
            return milliseconds/1000+","+(milliseconds/100)%10;
        }
        if(milliseconds==-1){
            return "";
        }
        return "0";
    }
}
