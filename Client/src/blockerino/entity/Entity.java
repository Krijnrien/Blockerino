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
import java.util.ArrayList;
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

    protected AABB leftCollision;
    protected AABB rightCollision;
    protected AABB topCollision;
    protected AABB bottomCollision;

    protected Vector2f velocity = new Vector2f(0, 0);
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

        leftCollision = new AABB(new Vector2f(0, 0), collWidth, _scale.y - 0.5f);
        rightCollision = new AABB(new Vector2f(0, 0), collWidth, _scale.y - 0.5f);
        topCollision = new AABB(new Vector2f(0, 0), scale.x - 0.5f, collHeight);
        bottomCollision = new AABB(new Vector2f(0, 0), scale.x - 0.5f, scale.y / 2);
    }

    public void renderCollision(Graphics2D _graphics2D, AffineTransform _projectionViewMatrix) {
        leftCollision.render(_graphics2D, _projectionViewMatrix);
        rightCollision.render(_graphics2D, _projectionViewMatrix);
        topCollision.render(_graphics2D, _projectionViewMatrix);
        bottomCollision.render(_graphics2D, _projectionViewMatrix);
    }

    /**
     * Test if the collisions are colliding with the world
     * TODO: Clean-up / abstract it more
     */
    public void testCollision(){
        List<Chunk> chunks = new ArrayList<>();

        //Get chunk from bounding corners of player
        Chunk topLeft = PlayState.world.getChunk(new Vector2f(position.x - (0.5f * scale.x), position.y - (0.5f * scale.y)));
        if (topLeft != null){
            chunks.add(topLeft);
        }

        Chunk topRight = PlayState.world.getChunk(new Vector2f(position.x + (0.5f * scale.x), position.y - (0.5f * scale.y)));
        if (topRight != null){
            chunks.add(topRight);
        }

        Chunk bottomLeft = PlayState.world.getChunk(new Vector2f(position.x - (0.5f * scale.x), position.y + (0.5f * scale.y)));
        if (bottomLeft != null){
            chunks.add(bottomLeft);
        }

        Chunk bottomRight = PlayState.world.getChunk(new Vector2f(position.x + (0.5f * scale.x), position.y + (0.5f * scale.y)));
        if (bottomRight != null){
            chunks.add(bottomRight);
        }

        List<AABB> leftCollisions = new ArrayList<>();
        List<AABB> rightCollisions = new ArrayList<>();
        List<AABB> topCollisions = new ArrayList<>();
        List<AABB> bottomCollisions = new ArrayList<>();

        for (int i = 0; i < chunks.size(); i++) {

            List<AABB> leftCollisionsChunk = chunks.get(i).getBlockCollisions(leftCollision);
            leftCollisions.addAll(leftCollisionsChunk);

            List<AABB> rightCollisionsChunk = chunks.get(i).getBlockCollisions(rightCollision);
            rightCollisions.addAll(rightCollisionsChunk);

            List<AABB> topCollisionsChunk = chunks.get(i).getBlockCollisions(topCollision);
            topCollisions.addAll(topCollisionsChunk);

            List<AABB> bottomCollisionsChunk = chunks.get(i).getBlockCollisions(bottomCollision);
            bottomCollisions.addAll(bottomCollisionsChunk);
        }

        if (leftCollisions.size() > 0){
            leftCollision.setColliding(true);
            velocity.x = 0;
        } else {
            leftCollision.setColliding(false);
        }

        if (rightCollisions.size() > 0){
            velocity.x = 0;
            rightCollision.setColliding(true);
        } else {
            rightCollision.setColliding(false);
        }

        if (topCollisions.size() > 0){
            velocity.y = 0;
            topCollision.setColliding(true);
        } else {
            topCollision.setColliding(false);
        }

        if (bottomCollisions.size() > 0){
            velocity.y = 0;
            bottomCollision.setColliding(true);
        } else {
            bottomCollision.setColliding(false);
        }
    }

    public void updateCollisions(){
        float collWidth = scale.x / 4;
        float collHeight = scale.y / 4;

        leftCollision.setPosition(new Vector2f(position.x - (scale.x / 2) + (collWidth / 2), position.y));
        rightCollision.setPosition(new Vector2f(position.x + (scale.x / 2) - (collWidth / 2), position.y));
        topCollision.setPosition(new Vector2f(position.x, position.y - (scale.y / 2) + (collHeight / 2)));
        bottomCollision.setPosition(new Vector2f(position.x, position.y + (scale.y / 2 / 2)));
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
