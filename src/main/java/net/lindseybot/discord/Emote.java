package net.lindseybot.discord;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Emote {

    private long id;
    private String name;
    private boolean animated;

    public Emote(long id, String name, boolean animated) {
        this.id = id;
        this.name = name;
        this.animated = animated;
    }

    /**
     * @return If this emote is unicode.
     */
    public boolean isUnicode() {
        return this.id == 0;
    }

    /**
     * Creates an emote from an unicode emoji codepoint.
     *
     * @param code Unicode.
     * @return Emote.
     */
    public static Emote ofUnicode(String code) {
        return new Emote(0, code, false);
    }

    /**
     * Returns this emote's mention representation, used in messages.
     *
     * @return Emote's mention.
     */
    public String asMention() {
        if (isUnicode()) {
            return this.name;
        } else {
            return "<" + (isAnimated() ? "a" : "") + ":" + this.getName() + ":" + this.getId() + ">";
        }
    }

    /**
     * Returns this emote's reaction representation, used in reactions.
     *
     * @return Emote's reaction name.
     */
    public String asReaction() {
        if (isUnicode()) {
            return this.name;
        } else {
            return this.getName() + ":" + this.getId();
        }
    }

}
