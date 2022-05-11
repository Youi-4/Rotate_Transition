/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rotate_transition;

import javafx.animation.KeyFrame;
import javafx.animation.RotateTransition;  
import javafx.animation.Timeline;
import javafx.application.Application;  
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;  
import javafx.scene.Scene;  
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;  
import javafx.scene.shape.Rectangle;  
import javafx.scene.transform.Rotate;  
import javafx.stage.Stage;  
import javafx.util.Duration;  

/**
 *
 * @author User
 */
public class Rotate_Transition extends Application {
    
    public Pane canvas;
     @Override
    public void start(final Stage primaryStage) {

        canvas = new Pane();
        final Scene scene = new Scene(canvas, 500, 400);
        Rectangle rect = new Rectangle(50, 50);
        Rectangle smlrect = new Rectangle(100,50);
        
        rect.setFill(Color.NAVY);
        canvas.getChildren().add(rect);
        canvas.getChildren().add(smlrect);
        primaryStage.setTitle("Rotate Transition example");
        primaryStage.setScene(scene);
        primaryStage.show();
        RotateTransition rotate = new RotateTransition();  
          
        //Setting Axis of rotation   
        rotate.setAxis(Rotate.Z_AXIS);  
          
        // setting the angle of rotation   
        rotate.setByAngle(90);  
          
        //setting cycle count of the rotation   
        rotate.setCycleCount(1);  
          
        //Setting duration of the transition   
        rotate.setDuration(Duration.millis(150));  
          
        //the transition will be auto reversed by setting this to true   
          
              
        //setting Rectangle as the node onto which the   
// transition will be applied  
        rotate.setNode(smlrect);  
          
        //playing the transition   
        
        smlrect.relocate(200,175);
        rect.relocate(100, 100);
        

        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(10), new EventHandler<ActionEvent>() {

            double deltaX = 2;
            double deltaY = 2;

            @Override
            public void handle(final ActionEvent t) {
                rect.setLayoutX(rect.getLayoutX() + deltaX);
                rect.setLayoutY(rect.getLayoutY() + deltaY);

                final Bounds bounds = canvas.getBoundsInLocal();
                final boolean atRightBorder = rect.getLayoutX() >= (bounds.getMaxX()-rect.getWidth() );
                final boolean atLeftBorder = rect.getLayoutX() <= (bounds.getMinX() );
                final boolean atBottomBorder = rect.getLayoutY() >= (bounds.getMaxY()-rect.getHeight());
                final boolean atTopBorder = rect.getLayoutY() <= (bounds.getMinY());
                if (atRightBorder || atLeftBorder) {
                    deltaX *= -1;
                    smlrect.setFill(Color.color(Math.random(),Math.random(),Math.random()));
                    rotate.play(); 
                }
                if (atBottomBorder || atTopBorder) {
                    deltaY *= -1;
                    smlrect.setFill(Color.color(Math.random(),Math.random(),Math.random()));
                    rotate.play(); 
                }
            }
        }));

        loop.setCycleCount(Timeline.INDEFINITE);
        loop.play();
        
    }
    public static void main(String[] args) {  
        launch(args);  
    }  
  
}  