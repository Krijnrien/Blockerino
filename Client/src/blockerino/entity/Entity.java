package blockerino.entity;

import blockerino.graphics.Sprite;
import blockerino.resources.Block;
import blockerino.states.PlayState;
import blockerino.util.AABB;
import blockerino.util.Vector2f;
import blockerino.world.Chunk;

import javax.xml.bind.annotation.XmlTransient;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.List;

//TODO extends world

@XmlTransient
public abstract class Entity extends WorldObject {

    //region Class variables
    private Sprite sprite;// Texture
    protected String textureName;
    private int weight;//weight of entity
    private Vector2f size;
    private AABB hitBounds;// Bounds entity for hits
    private AABB bounds; //bounds entity for collision

    private AABB leftCollision;
    private AABB rightCollision;
    private AABB topCollision;
    private AABB bottomCollision;
    //endregion

    public void setBothBounds() {
        Vector2f _origin = position;
        Vector2f _scale = getScale();

        bounds = new AABB(_origin, _scale.x, _scale.y);
        hitBounds = new AABB(new Vector2f(_origin.x + (scale.x / 2), _origin.y), _scale.x, _scale.y);
    }

    public void setCollissions() {
        Vector2f _origin = position;
        Vector2f _scale = getScale();

        float collWidth = scale.x / 4;
        float collHeight = scale.y / 4;

        leftCollision = new AABB(new Vector2f(_origin.x - (scale.x / 2) + (collWidth / 2), _origin.y), collWidth, _scale.y - 0.5f);
        rightCollision = new AABB(new Vector2f(_origin.x + (scale.x / 2) - (collWidth / 2), _origin.y), collWidth, _scale.y - 0.5f);
        topCollision = new AABB(new Vector2f(_origin.x, _origin.y - (scale.y / 2) + (collHeight / 2)), scale.x - 0.5f, collHeight);
        bottomCollision = new AABB(new Vector2f(_origin.x, _origin.y + (scale.y / 2) - (collHeight / 2)), scale.x - 0.5f, collHeight);
    }

    public void renderCollision(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
        leftCollision.render(_graphics2D, _projectionViewMatrix);
        rightCollision.render(_graphics2D, _projectionViewMatrix);
        topCollision.render(_graphics2D, _projectionViewMatrix);
        bottomCollision.render(_graphics2D, _projectionViewMatrix);
    }

    public void testCollision(){
        Chunk chunk = PlayState.world.getChunk(position);

        if (chunk != null){
            if (chunk.getBlockCollisions(leftCollision)){
                leftCollision.setColliding(true);
            } else {
                leftCollision.setColliding(false);
            }

            if (chunk.getBlockCollisions(rightCollision)){
                rightCollision.setColliding(true);
            } else {
                rightCollision.setColliding(false);
            }

            if (chunk.getBlockCollisions(topCollision)){
                topCollision.setColliding(true);
            } else {
                topCollision.setColliding(false);
            }

            if (chunk.getBlockCollisions(bottomCollision)){
                bottomCollision.setColliding(true);
            } else {
                bottomCollision.setColliding(false);
            }

        } else{
            System.out.println("chunk == null");
        }
    }

    public void updateCollisions(){
        float collWidth = scale.x / 4;
        float collHeight = scale.y / 4;

        System.out.println(scale.toString());

        leftCollision.setPosition(new Vector2f(position.x - (scale.x / 2) + (collWidth / 2), position.y));
        rightCollision.setPosition(new Vector2f(position.x + (scale.x / 2) - (collWidth / 2), position.y));
        topCollision.setPosition(new Vector2f(position.x, position.y - (scale.y / 2) + (collHeight / 2)));
        bottomCollision.setPosition(new Vector2f(position.x, position.y + (scale.y / 2) - (collHeight / 2)));
    }

    //region Abstract methods
    public abstract void update();

    public abstract void render(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix);
    //endregion

    //region Getters and setters
    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Vector2f getSize() {
        return size;
    }

    public void setSize(Vector2f size) {
        this.size = size;
    }

    public AABB getHitBounds() {
        return hitBounds;
    }

    public void setHitBounds(AABB hitBounds) {
        this.hitBounds = hitBounds;
    }

    public AABB getBounds() {
        return bounds;
    }

    public void setBounds(AABB bounds) {
        this.bounds = bounds;
    }

    //endregion
}
