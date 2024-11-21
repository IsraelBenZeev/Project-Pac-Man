package pac_man;

import java.awt.*;
import java.io.IOException;

public interface MyFunctions{

    public void setValues() throws IOException;

    public void draw(Graphics2D g2) throws InterruptedException;
    public void update() throws InterruptedException;


}
