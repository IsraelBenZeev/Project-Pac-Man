package pac_man;

import java.awt.*;
import java.io.IOException;

public interface MyFunctions{

    public void setValues() throws IOException;

    public void draw(Graphics2D g2);
    public void update() throws InterruptedException;


}
