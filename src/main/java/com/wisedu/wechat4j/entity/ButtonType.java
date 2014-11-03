package com.wisedu.wechat4j.entity;

public enum ButtonType{
    CLICK("click"),
    VIEW("view"),
    SCANCODEPUSH("scancode_push"),
    SCANCODEWAITMSG("scancode_waitmsg"),
    PICSYSPHOTO("pic_sysphoto"),
    PICPHOTOORALBUM("pic_photo_or_album"),
    PICWEIXIN("pic_weixin"),
    LOCATIONSELECT("location_select");

    private final String symbol;

    ButtonType(String symbol){
        this.symbol = symbol;
    }

    public static ButtonType getInstance(String symbol){
        if (symbol == null){
            return null;
        }
        for (ButtonType buttonType: ButtonType.values()){
            if (buttonType.symbol.equals(symbol)){
                return buttonType;
            }
        }
        return null;
    }

    @Override public String toString(){
        return symbol;
    }
}
