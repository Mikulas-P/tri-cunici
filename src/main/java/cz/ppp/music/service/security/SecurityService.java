package cz.ppp.music.service.security;

@SuppressWarnings("unused")
public interface SecurityService {
    String currentUserUsername();
    void autoLogin(String username, String password);
}
