package langame;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextCharacter;

public class Hero {

    private TerminalPosition position;

    private TextCharacter textCharacter;

    public Hero(TerminalPosition position, TextCharacter textCharacter) {
        this.position = position;
        this.textCharacter = textCharacter;
    }

    public Hero(TerminalPosition position) {
        this.position = position;
        this.textCharacter = new TextCharacter('@');
    }
}
