package com.BooksShopBackend.REST.API.models.ordersDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderGetDTO {

    private Boolean returnSecureToken;

    public OrderGetDTO(){
        super();
    }

    public OrderGetDTO(Boolean returnSecureToken){

        this.returnSecureToken = returnSecureToken;
    }

//    public Boolean getReturnSecureToken() {
//        return this.returnSecureToken;
//    }
//    public void setReturnSecureToken(Boolean returnSecureToken) {
//        this.returnSecureToken = returnSecureToken;
//    }
}
