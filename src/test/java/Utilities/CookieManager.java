package Utilities;

import org.openqa.selenium.Cookie;

public class CookieManager extends base{

    public void addCookie(Cookie cookie) {
        getDriver().manage().addCookie(cookie);
    }

    public void deleteCookie(Cookie cookie) {
        getDriver().manage().deleteCookie(cookie);
    }

    public boolean isCookiePresent(Cookie cookie) {
        return getDriver().manage().getCookieNamed(cookie.getName()) != null;
    }

    public Cookie buildCookie(String name, String value) {
        return new Cookie.Builder(name, value)
                .domain("the-internet.herokuapp.com")
                .build();
    }
}
