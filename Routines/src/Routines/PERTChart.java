
package Routines;

import java.util.LinkedList;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;



public class PERTChart extends Pane {
    
        //////////////////////////////////////////////  DATAFIELDS  /////////
    
    private double startX = 15;
    private double startY = 15;
    private double previousX = 0;
    private double previousY = 0;
    private double width = 175;
    private double height = 100;
    private double spacing = 75;
    
    
    
    
    
    
        /////////////////////////////////////////  CONSTRUCTORS  ////////////
    
    public PERTChart(Routine routine){
        LinkedList<Integer> criticals = new LinkedList<>();
        for(int i = 0; i < routine.getRoutineTasks().size()-1; i++){
            criticals.add(i);
            int max = i;
            for(int j = i + 1; j < routine.getRoutineTasks().size(); j++){
                
                if(routine.getRoutineTasks().get(j).getStartTime().compareTo(routine.getRoutineTasks().get(i).getEndTime())<0){
                    if(routine.getRoutineTasks().get(max).getDuration() < routine.getRoutineTasks().get(j).getDuration()){
                        for(int k = 0; k < criticals.size(); k++){
                            if(criticals.get(k) == max){
                                criticals.remove(k);
                                criticals.add(j);
                            }
                        }
                        max = j;
                    }
                    i = j;
                }
            }
        }
        criticals.add(routine.getRoutineTasks().size() - 1);
        for(int i = 0; i < routine.getRoutineTasks().size(); i++){
            boolean criticalPath =  criticals.contains(i) ? true : false;
            if(i > 0 && routine.getRoutineTasks().get(i).getStartTime().compareTo(routine.getRoutineTasks().get(i-1).getEndTime())<0){
                startY += height + spacing;
                startX -= (width + spacing);
                previousX = startX - 35;
            }
            else{
                startY = 15;
                previousY = startY;
                previousX = startX - 35;
            }
            this.getChildren().add(new Summary(routine.getRoutineTasks().get(i), startX, startY, criticalPath));
            if(i != 0){
                double[] coords = {previousX, previousY + height/2, startX + 35, startY + height/2}; //35 is padding in summary
                this.getChildren().add(new Connector(coords, criticalPath));
            }
        startX += width + spacing;
        }
    }//end constructor
    

    
    
    
    
        ///////////////////////////////////////////////  INNER CLASSES  ///////
    
    public class Summary extends Pane {
        
            /////////////////////////////  DATAFIELDS  /////////
        private Task task;

        
            /////////////////////////////  CONSTRUCTORS  ///////
        
        public Summary(Task task, double x, double y, boolean criticalPath){
            Text words = new Text();
            words.setFont(new Font(12));
            this.task = task;
            words.setX(x + 50);
            words.setY(y + 50);
            words.setWrappingWidth(width-50);
            double cost = 0.0;
            for(int i = 0; i < this.task.getAssignedResources().size(); i++){
                if(this.task.getAssignedResources().get(i).getUnits() == CostBasis.FLAT_FEE){
                    cost += this.task.getAssignedResources().get(i).getCost() / this.task.getAssignedResources().get(i).getAssignedTo().size();
                }
                else{
                    cost += this.task.getAssignedResources().get(i).getCost() * this.task.getDuration();
                }
            }
            words.setText("Name: " + this.task.getName() + "\nDuration: " + this.task.getDuration() + " " + this.task.getUnits() + "\nProjected Cost: " + cost + "\n" );
            Rectangle container = new Rectangle(words.getX() - 15, words.getY() - 35, width, height);
            container.getStyleClass().add(criticalPath ? "pert-critical": "pert-normal");
            words.getStyleClass().add(criticalPath ? "pert-critical-words": "pert-normal-words");
            this.getChildren().addAll(container, words);
        }//end constructor
    }//end Summary
    
    
    
    
    public class Connector extends Pane{
        
            ///////////////////////////////////  DATAFIELDS  ////////
        
        private Line body = new Line();
        private Line bodyTail = new Line();
        private Line bodyHead = new Line();
        private Line head1 = new Line();
        private Line head2 = new Line();
        private double[] coords = new double[4]; //startX, startY, endX, endY
        
        
            /////////////////////////////////  CONSTRUCTORS  ////////
        
        public Connector(double[] coords, boolean criticalPath){
            this.coords = coords;
            if(coords[1] != coords[3]){
                bodyTail.setStartX(coords[0]);
                bodyTail.setStartY(coords[1]);
                bodyTail.setEndX(coords[0] + 12);
                bodyTail.setEndY(coords[1]);
                body.setStartX(coords[0]+12);
                body.setStartY(coords[1]);
                body.setEndX(coords[2]-25);
                body.setEndY(coords[3]);
                bodyHead.setStartX(coords[2]-25);
                bodyHead.setStartY(coords[3]);
                bodyHead.setEndX(coords[2]);
                bodyHead.setEndY(coords[3]);
                body.getStyleClass().add(criticalPath ? "pert-critical": "pert-normal");
                bodyTail.getStyleClass().add(criticalPath ? "pert-critical": "pert-normal");
                bodyHead.getStyleClass().add(criticalPath ? "pert-critical": "pert-normal");
                this.getChildren().addAll(bodyTail, bodyHead, body);
            }
            else{
                body.setStartX(coords[0]);
                body.setStartY(coords[1]);
                body.setEndX(coords[2]);
                body.setEndY(coords[3]);
                body.getStyleClass().add(criticalPath ? "pert-critical": "pert-normal");
                this.getChildren().add(body);
            }
            head1.setStartX(coords[2]-20);
            head2.setStartX(coords[2]-20);
            head1.setStartY(coords[3]-10);
            head2.setStartY(coords[3]+10);
            head1.setEndX(coords[2]);
            head2.setEndX(coords[2]);
            head1.setEndY(coords[3]);
            head2.setEndY(coords[3]);
            head1.getStyleClass().add(criticalPath ? "pert-critical": "pert-normal");
            head2.getStyleClass().add(criticalPath ? "pert-critical": "pert-normal");
            this.getChildren().addAll(head1, head2);
        }//end constructor
    }//end Connector
    
}//end PERTChart
