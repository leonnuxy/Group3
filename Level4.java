import java.util.ConcurrentModificationException;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Level4 extends LevelActions {
	
	private double SPEED = 2;
	private int rad = 1;
	private int MID = 400;
	private int LEN = 100;
	private int CELL = 20;
	boolean collision = false;
	
	@Override
	public void start(Stage primaryStage){
		try {
		Pane root = new Pane();
		root.setStyle("-fx-background-image: url(Pane.png);");
		root.setPrefSize(APP_W, APP_H);
		
		Polygon poly1 = new Polygon(MID,LEN,MID+CELL,(LEN*2)-CELL,LEN+MID,LEN*2,MID+CELL,(LEN*2)+CELL,MID,MID-LEN,MID-CELL,(LEN*2)+CELL,MID-LEN,LEN*2,MID-CELL,(LEN*2)-CELL,MID,LEN);
		Polygon poly2 = new Polygon(MID,LEN,MID+CELL,(LEN*2)-CELL,LEN+MID,LEN*2,MID+CELL,(LEN*2)+CELL,MID,MID-LEN,MID-CELL,(LEN*2)+CELL,MID-LEN,LEN*2,MID-CELL,(LEN*2)-CELL,MID,LEN);
		Polygon poly3 = new Polygon(MID,LEN,MID+CELL,(LEN*2)-CELL,LEN+MID,LEN*2,MID+CELL,(LEN*2)+CELL,MID,MID-LEN,MID-CELL,(LEN*2)+CELL,MID-LEN,LEN*2,MID-CELL,(LEN*2)-CELL,MID,LEN);
		Polygon poly4 = new Polygon(MID,LEN,MID+CELL,(LEN*2)-CELL,LEN+MID,LEN*2,MID+CELL,(LEN*2)+CELL,MID,MID-LEN,MID-CELL,(LEN*2)+CELL,MID-LEN,LEN*2,MID-CELL,(LEN*2)-CELL,MID,LEN);
		Circle cir1 = new Circle(MID,LEN*2,rad); Circle cir2 = new Circle(MID,MID+(LEN*2),rad); 
		Circle cir3 = new Circle(LEN*2,MID,rad); Circle cir4 = new Circle(MID+(LEN*2),MID,rad);
		
		//Creates the a new Path Transition for each object
		PathTransition trans = new PathTransition();
		trans.setNode(poly1);
		trans.setPath(cir1);
		trans.setDuration(Duration.seconds(SPEED));
		trans.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		trans.setCycleCount(PathTransition.INDEFINITE);
		trans.play();
		
		PathTransition trans2 = new PathTransition();
		trans2.setNode(poly2);
		trans2.setPath(cir2);
		trans2.setDuration(Duration.seconds(SPEED));
		trans2.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		trans2.setCycleCount(PathTransition.INDEFINITE);
		trans2.play();
		
		PathTransition trans3 = new PathTransition();
		trans3.setNode(poly3);
		trans3.setPath(cir3);
		trans3.setDuration(Duration.seconds(SPEED));
		trans3.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		trans3.setCycleCount(PathTransition.INDEFINITE);
		trans3.play();
		
		PathTransition trans4 = new PathTransition();
		trans4.setNode(poly4);
		trans4.setPath(cir4);
		trans4.setDuration(Duration.seconds(SPEED));
		trans4.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
		trans4.setCycleCount(PathTransition.INDEFINITE);
		trans4.play();
		
		root.getChildren().addAll(poly4,poly3,poly2,poly1);

		
		Group snakeBody = new Group();
		snake = snakeBody.getChildren();
		Collectible aCol = new Collectible(50,50);
		Rectangle col = aCol.getCol();
		Obstacle borderRight = new Obstacle(801,0,1,800);
		Rectangle rightBorder = borderRight.getObs();
		Obstacle borderLeft = new Obstacle(-2,0,1,800);
		Rectangle leftBorder = borderLeft.getObs();
		Obstacle borderBottom = new Obstacle(0,801,800,1);
		Rectangle bottomBorder = borderBottom.getObs();
		Obstacle borderTop = new Obstacle(0,-2,800,1);
		Rectangle topBorder = borderTop.getObs();
		root.getChildren().add(snakeBody);
		root.getChildren().add(rightBorder);
		root.getChildren().add(leftBorder);
		root.getChildren().add(bottomBorder);
		root.getChildren().add(topBorder);
		root.getChildren().add(col);
		
		KeyFrame frame = new KeyFrame(Duration.seconds(Difficulty.getDifficulty()), event ->{
			if (!running)
				return;
			
			boolean toRemove = snake.size()>1;
			Node tail = toRemove ? snake.remove(snake.size()-1) : snake.get(0);
			double tailX = tail.getTranslateX();
			double tailY = tail.getTranslateY();
			switch (direction) {
				case UP:
					tail.setTranslateX(snake.get(0).getTranslateX());
					tail.setTranslateY(snake.get(0).getTranslateY() - TILE_SIZE);
					break;
				case DOWN:
					tail.setTranslateX(snake.get(0).getTranslateX());
					tail.setTranslateY(snake.get(0).getTranslateY() + TILE_SIZE);
					break;
				case LEFT:
					tail.setTranslateX(snake.get(0).getTranslateX() - TILE_SIZE);
					tail.setTranslateY(snake.get(0).getTranslateY());
					break;
				case RIGHT:
					tail.setTranslateX(snake.get(0).getTranslateX() + TILE_SIZE);
					tail.setTranslateY(snake.get(0).getTranslateY() );
				
			}
			if (toRemove)
				snake.add(0, tail);
			moved = true;
			/*for (Node rect: snake) {				
				if (rect != tail && tailX == rect.getTranslateX() && tailY == rect.getTranslateY()) {
					restartGame();
				}
			}*/
			if (snakeBody.getBoundsInParent().intersects(rightBorder.getBoundsInParent()) || 
					snakeBody.getBoundsInParent().intersects(leftBorder.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(bottomBorder.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(topBorder.getBoundsInParent())) {
				endGame();
				//System.out.println("Time Elapsed: " + TimerS.getTotalTime(timeStart, timeEnd));
			}
			
			if (snakeBody.getBoundsInParent().intersects(poly1.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(poly2.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(poly3.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(poly4.getBoundsInParent())) {
				endGame();
			}
			
			/*if (snakeBody.getBoundsInParent().intersects(slamDOWN.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(slamUP.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(slamDOWN1.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(slamUP1.getBoundsInParent()) ||
					snakeBody.getBoundsInParent().intersects(slamMID.getBoundsInParent())) {
				restartGame();
				//System.out.println("Time Elapsed: " + TimerS.getTotalTime(timeStart, timeEnd));
			}
			
			/* collision with collectible */
			if (tail.getBoundsInParent().intersects(col.getBoundsInParent())){
				aCol.setXPos();
				aCol.setYPos();
				col.relocate(aCol.getXPos(), aCol.getYPos());       
				col.getBoundsInParent();
				root.getChildren().remove(col);
				root.getChildren().add(col);
				
				Rectangle rect = new Rectangle(TILE_SIZE, TILE_SIZE);
				rect.setFill(Color.rgb(241, 249, 12));
				rect.setTranslateX(tailX);
				rect.setTranslateY(tailY);
				Score.setScore(1);
				snake.add(rect);
				
	
			}
			/*if (obs.getBoundsInParent().intersects(col.getBoundsInParent())) {
				aCol.setXPos();
				aCol.setYPos();
				col.relocate(aCol.getXPos(), aCol.getYPos()); 
			}
			if (col.getBoundsInParent().intersects(obs.getBoundsInParent())) {
				aCol.setXPos();
				aCol.setYPos();
				col.relocate(aCol.getXPos(), aCol.getYPos()); 
			}
			if (tail.getBoundsInParent().intersects(tail2.getBoundsInParent())) {
				restartGame();
			}*/
			
			if (Score.getScore() == score + 1) {
				primaryStage.close();
				timeline.stop();
				Level5 level2 = new Level5();
				level2.start(primaryStage);
				level2.stopGame();
				level2.startGame();
				
			}

			
		});
		
		timeline.getKeyFrames().add(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);
		Scene scene = new Scene(root);
		
		scene.setOnKeyPressed(event -> {
			if (!moved)
				return;

		    if (moved) {
			switch (event.getCode()) {
				case UP:
					if (direction != Direction.DOWN)
						direction = Direction. UP;
					break;
				case DOWN:
					if (direction != Direction.UP)
						direction = Direction.DOWN;
					break;
				case LEFT:
					if (direction != Direction.RIGHT)
						direction = Direction.LEFT;
					break;
				case RIGHT:
					if (direction != Direction.LEFT)
						direction = Direction.RIGHT;
					break;
			default:
				break;
			}
		}
		
		moved = false;	
	});
		this.primaryStage = primaryStage;
		primaryStage.setTitle("Snake");
		primaryStage.setScene(scene);
		primaryStage.show();
		startGame();
	}
	catch (ConcurrentModificationException e) {
		System.out.println(e.getMessage());
	}
}
}
