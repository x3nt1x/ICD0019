package reflection.serializer;

public class Post
{
    @Stored("post-title")
    private String title;

    @Stored
    private String text;

    @Stored
    private int replyCount;

    public Post() { }

    public Post(String title, String text)
    {
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString()
    {
        return String.format("Post{title='%s', text='%s', replyCount='%s'}", title, text, replyCount);
    }
}