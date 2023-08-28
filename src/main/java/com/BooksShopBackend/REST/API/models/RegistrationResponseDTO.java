package com.BooksShopBackend.REST.API.models;

public class RegistrationResponseDTO {
    private String localId;
    private String username;
    private String idToken;
    private String refreshToken;
    private final String expiresIn = "3600";

    public RegistrationResponseDTO() {
        super();
    }

    public RegistrationResponseDTO(String username, String idToken, String refreshToken) {
        super();
        this.username = username;
        this.idToken = idToken;
        this.refreshToken = refreshToken;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }
}



//    {
//            "idToken": "eyJhbGciOiJSUzI1NiIsImtpZCI6ImM2MGI5ZGUwODBmZmFmYmZjMTgzMzllY2Q0NGFjNzdmN2ZhNGU4ZDMiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vYm9va3Nob3Bhbmd1bGFyLTgyYTM4IiwiYXVkIjoiYm9va3Nob3Bhbmd1bGFyLTgyYTM4IiwiYXV0aF90aW1lIjoxNjkzMTY4OTM5LCJ1c2VyX2lkIjoiWWY1SWNKbFVOUVJvdW1tdnNjbEFuZklRUTVyMiIsInN1YiI6IllmNUljSmxVTlFSb3VtbXZzY2xBbmZJUVE1cjIiLCJpYXQiOjE2OTMxNjg5MzksImV4cCI6MTY5MzE3MjUzOSwiZW1haWwiOiJnYXdsaWtkYWFyZWtAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOmZhbHNlLCJmaXJlYmFzZSI6eyJpZGVudGl0aWVzIjp7ImVtYWlsIjpbImdhd2xpa2RhYXJla0BnbWFpbC5jb20iXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.jgB76IWMnrv7navDV87NIyVM82_XnJ8aXRFcl25I9z1Yr2JZfAQhSooXo72oiAyepvx-cIJRoXtg9OjhVy5lhA15UMS8sHKov4XEXLf9iNwh2-ldc66RYKRT9fEzSxGBcVKD6hRQn58whLT5q29ea5I04Wol9rwCJ3eFXKbfcLXkmC8PbVn9nmkpNXk1Qw2CLa1x0wD6UiNTLlvmLFRywvHaF12mILQLcT5CRGzNBpwlQCL-czX_qWdvcpU5DPDBJbb-XkP8sU7821BGV_GXgzhX4U7CzPrnY-PmZzymSbZfeauAjuk-HXjn9Ps4Refd0OblXhwfZRX8rT7J-CVz8Q",
//            "email": "gawlikdaarek@gmail.com",
//            "refreshToken": "AMf-vBxFdAb-RLo8hgeO-SL4MscPBvLMJU1iYd6GmLRAbIkU2e_W-vSBquTI5WXE7cbwCEpcKhfbEun6C4M6SoPyr-oV2_8Fr9d-oJSEJBWf1JKD0hA7bI4lLS2O2MKNmnGfh-FPavDI6Hmzow2Jb5lkKRkl9j2rc7VVCk3_-2k1RfpOuOXBK2rrd63ZILywNsGnu_RWsGwTHt5VRdUIdPXjIRQHlHXAUWlYwj7aN2cjlvvd0qwoDq0",
//            "expiresIn": "3600",
//            "localId": "Yf5IcJlUNQRoummvsclAnfIQQ5r2"
//    }