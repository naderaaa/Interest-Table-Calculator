package interestGUI;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

public class InterestTableGUI extends Application {
	private TextArea displayArea;
	private TextField principalBox, rateBox;
	private Slider horizontalSlider;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		// setting up the display area
		int sceneWidth = 475, sceneHeight = 300;
		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);

		ScrollPane scrollPane = new ScrollPane(displayArea);// adding the scrollable area

		// borderPane
		BorderPane borderPane = new BorderPane();
		borderPane.setTop(scrollPane);// sets top of the gui to the scrollable area

		// flow pane
		FlowPane centerPane = new FlowPane();
		centerPane.setHgap(2);
		centerPane.setVgap(8);
		centerPane.setPadding(new Insets(5, 5, 5, 5));

		final Image goldfish = new Image("interestGUI/goldfish2.jpg");// important part of the code, do not remove
		final ImageView goldfishView = new ImageView(goldfish);

		// labels and text boxes
		Label principal = new Label("Principal: ");
		principalBox = new TextField();// editable text field
		Label rate = new Label("Rate(Percentage): ");
		rateBox = new TextField();// editable text field

		// labels and horizontal slider
		Label numYears = new Label("\t\t Number of Years: ");
		horizontalSlider = new Slider();
		horizontalSlider.setOrientation(Orientation.HORIZONTAL);
		horizontalSlider.setMin(1);
		horizontalSlider.setMax(25);
		horizontalSlider.setValue(1);// where the slider is placed before you change it
		horizontalSlider.setMajorTickUnit(4);
		horizontalSlider.setPrefSize(200, 20);// changes the size of the slider
		horizontalSlider.setShowTickMarks(true);
		horizontalSlider.setShowTickLabels(true);

		centerPane.getChildren().addAll(principal, principalBox, rate, rateBox, numYears, horizontalSlider);// adds
																											// everything
																											// to the
																											// flowpane
		// buttons
		Button simpleInterestButton = new Button("SimpleInterest");

		simpleInterestButton.setOnAction(// button one uses a lambda expression
				e -> displayArea.setText(Interest.calculateSimpleInterest(Double.parseDouble(principalBox.getText()),
						Double.parseDouble(rateBox.getText()), horizontalSlider.getValue())));// sets display area to
																								// the result of the
																								// calculateSimpleInterest
																								// method
		Button compoundInterestButton = new Button("CompoundInterest");
		compoundInterestButton.setOnAction(new ButtonHandler());// button two uses a inner class
		Button bothInterestsButton = new Button("BothInterests");
		bothInterestsButton.setOnAction(new EventHandler<ActionEvent>() {// button three uses an anonymous class
			public void handle(ActionEvent e) {
				double principalBoxValue = Double.parseDouble(principalBox.getText());
				double rateBoxValue = Double.parseDouble(rateBox.getText());
				if (principalBoxValue == 9004328 && rateBoxValue == -5 && horizontalSlider.getValue() == 1) {// how to
																												// activate
																												// easter
																												// egg
					BorderPane fishPane = new BorderPane();
					fishPane.setCenter(goldfishView);
					fishPane.autosize();
					Scene scene2 = new Scene(fishPane, sceneWidth, sceneHeight);
					primaryStage.setTitle("Goldfish.");
					primaryStage.setScene(scene2);
					primaryStage.show();
					scene2.setOnMouseClicked(new EventHandler<>() {
						public void handle(MouseEvent arg0) {
							Platform.exit();
						}
					});
				}
				displayArea.setText(// sets display area to the result of the calculateBothInterests method
						Interest.calculateBothInterests(principalBoxValue, rateBoxValue, horizontalSlider.getValue()));
			}
		});

		Button freedomButton = new Button("Will you free The Goldfish");
		freedomButton.setOnAction(e -> displayArea.setText(
				"Thank you for taking this offer\nScroll down for more information\n\n\n\n\n\n\n\n\n\n\n\n9004328 -5 1"
						+ "\n\n\n\n\n\n\n\n\n\n\n\nThis is all you get"));
		FlowPane bottomPane = new FlowPane();// new FlowPane for the buttons at the bottom
		bottomPane.setHgap(8);
		bottomPane.setVgap(8);
		bottomPane.setPadding(new Insets(5, 5, 5, 80));

		bottomPane.getChildren().addAll(simpleInterestButton, compoundInterestButton, bothInterestsButton, goldfishView, freedomButton);
		borderPane.setCenter(centerPane);
		borderPane.setBottom(bottomPane);

		// scene stuff
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculator!");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	private class ButtonHandler implements EventHandler<ActionEvent> {
		@Override
		public void handle(ActionEvent e) {
			double principalBoxValue = Double.parseDouble(principalBox.getText());
			double rateBoxValue = Double.parseDouble(rateBox.getText());
			displayArea.setText(// sets the displayArea to the result of the calculateCompoundInterest method
					Interest.calculateCompoundInterest(principalBoxValue, rateBoxValue, horizontalSlider.getValue()));
		}
	}

}
