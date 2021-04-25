package net.lindseybot.discord;

import net.notfab.lindsey.shared.rpc.FUser;

import java.awt.*;

public class EmbedBuilder {

    private static final String ZERO_WIDTH_SPACE = "\u200E";
    private final Embed embed;

    public EmbedBuilder() {
        this.embed = new Embed();
    }

    /**
     * Sets the title of this embed.
     *
     * @param title Title.
     * @return Builder for chaining.
     */
    public EmbedBuilder title(Message title) {
        this.embed.setTitle(title);
        return this;
    }

    /**
     * Sets the description of this embed.
     *
     * @param description Description.
     * @return Builder for chaining.
     */
    public EmbedBuilder description(Message description) {
        this.embed.setDescription(description);
        return this;
    }

    /**
     * Sets the color of this embed.
     *
     * @param color AWT Color.
     * @return Builder for chaining.
     */
    public EmbedBuilder color(Color color) {
        this.embed.setColor(color.getRGB());
        return this;
    }

    /**
     * Sets the color of this embed.
     *
     * @param color RGB color.
     * @return Builder for chaining.
     */
    public EmbedBuilder color(int color) {
        this.embed.setColor(color);
        return this;
    }

    /**
     * Sets the url for the title of this embed. This is ignored if no title is set.
     *
     * @param url URL.
     * @return Builder for chaining.
     */
    public EmbedBuilder url(String url) {
        this.embed.setUrl(url);
        return this;
    }

    /**
     * Sets the timestamp of this embed.
     *
     * @param timestamp Timestamp (Unix time).
     * @return Builder for chaining.
     */
    public EmbedBuilder timestamp(long timestamp) {
        this.embed.setTimestamp(timestamp);
        return this;
    }

    /**
     * Sets the thumbnail of this embed (top-right image).
     *
     * @param url Image url.
     * @return Builder for chaining.
     */
    public EmbedBuilder thumbnail(String url) {
        this.embed.setThumbnail(url);
        return this;
    }

    /**
     * Sets the image of this embed (below description).
     *
     * @param url Image url.
     * @return Builder for chaining.
     */
    public EmbedBuilder image(String url) {
        this.embed.setImage(url);
        return this;
    }

    /**
     * Sets the author block of this embed to a user.
     *
     * @param user Fake user.
     * @return Builder for chaining.
     */
    public EmbedBuilder author(FUser user) {
        Embed.Author author = new Embed.Author();
        author.setName(Message.raw(user.getName()));
        author.setIcon(user.getAvatarUrl());
        author.setUrl(null);
        this.embed.setAuthor(author);
        return this;
    }

    /**
     * Sets the author block of this embed to the specified text.
     *
     * @param name Name
     * @param url  Url.
     * @param icon Icon.
     * @return Builder for chaining.
     */
    public EmbedBuilder author(Message name, String url, String icon) {
        Embed.Author author = new Embed.Author();
        author.setName(name);
        author.setIcon(icon);
        author.setUrl(url);
        this.embed.setAuthor(author);
        return this;
    }

    /**
     * Sets the footer block of this embed to a user.
     *
     * @param user Fake user.
     * @return Builder for chaining.
     */
    public EmbedBuilder footer(FUser user) {
        Embed.Footer footer = new Embed.Footer();
        footer.setText(Message.raw(user.getName()));
        footer.setIcon(user.getAvatarUrl());
        this.embed.setFooter(footer);
        return this;
    }

    /**
     * Sets the footer block of this embed to the specified text.
     *
     * @param text Text.
     * @param icon Icon.
     * @return Builder for chaining.
     */
    public EmbedBuilder footer(Message text, String icon) {
        Embed.Footer footer = new Embed.Footer();
        footer.setText(text);
        footer.setIcon(icon);
        this.embed.setFooter(footer);
        return this;
    }

    /**
     * Adds a field to this embed.
     *
     * @param name   Name of the field.
     * @param value  Value of the field.
     * @param inline If this field is to be displayed in-line (up to 3 per line).
     * @return Builder for chaining.
     */
    public EmbedBuilder field(Message name, Message value, boolean inline) {
        Embed.Field field = new Embed.Field();
        field.setName(name);
        field.setValue(value);
        field.setInline(inline);
        this.embed.getFields().add(field);
        return this;
    }

    /**
     * Adds a blank field to this embed. Useful for spacing.
     *
     * @param inline If this field is to be displayed in-line (up to 3 per line)
     * @return Builder for chaining.
     */
    public EmbedBuilder blankField(boolean inline) {
        Embed.Field field = new Embed.Field();
        field.setName(Message.raw(ZERO_WIDTH_SPACE));
        field.setValue(Message.raw(ZERO_WIDTH_SPACE));
        field.setInline(inline);
        this.embed.getFields().add(field);
        return this;
    }

    /**
     * Builds this embed.
     *
     * @return Embed.
     */
    public Embed build() {
        return this.embed;
    }

}
