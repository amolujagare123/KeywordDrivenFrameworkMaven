import com.project.keywords.keywordEngine.KeyWordEngine;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest {

    KeyWordEngine keyWordEngine;
    @Test
    public void loginPageTest() throws IOException {
        keyWordEngine = new KeyWordEngine();
        keyWordEngine.startEngine("login");
    }
}
