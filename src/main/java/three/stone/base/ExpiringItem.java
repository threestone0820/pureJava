package three.stone.base;

public interface ExpiringItem {
    long id();

    long expiration();

    int getPosition();

    void setPosition(int position);

    void expired();
}
