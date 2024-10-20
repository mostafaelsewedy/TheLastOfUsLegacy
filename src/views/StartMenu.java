package views;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.characters.Direction;
import model.characters.Explorer;
import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Vaccine;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.event.ActionEvent;

import java.io.IOException;
import java.util.ArrayList;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.animation.ScaleTransition;
import javafx.scene.control.*;
import javafx.scene.effect.BoxBlur;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.shape.*;
import javafx.event.EventHandler;

public class StartMenu extends Application {

	private Pane root = new Pane();
	private Line line = new Line();
	private Line lineCred = new Line();
	private Line line2Cred = new Line();
	private Line lineSelect = new Line();
	private GridPane herosSelect = new GridPane();
	private int numF = 0;
	private int numE = 0;
	private int numM = 0;
	private int x = 0;
	private VBox herosGameplay = new VBox(10);
	private HBox data = new HBox(10);
	private HBox buttons = new HBox(10);
	private GridPane moveButtons = new GridPane();
	private Text text = new Text();
	private Hero currHero;
	private GridPane map = new GridPane();
	private Stage myStage = new Stage();

	public Parent createStart() {
		root.getChildren().clear();
		addBackground("start-bg.gif");
		addTitleStart();
		addLineStart();
		addButtonStart("Single Player", 0);
		addButtonStart("How-To", 1);
		addButtonStart("Credits", 2);
		addButtonStart("Exit", 3);
		lineAnimationStart();
		return root;
	}

	public Parent createCredits() {
		root.getChildren().clear();
		addBackground("credits-bg.gif");
		addTitleCredits();
		addLineCredits();
		addLine2Credits();
		addTextCredits("Mostafa El-Sewedy 55-5414", 0);
		addTextCredits("Mohamed Sweidan 55-5201", 1);
		addTextCredits("Mohamed Tobar 55-5914", 2);
		addButtonCredits("Exit", 3);
		addButtonCredits("Back", 4);
		lineAnimationCredits();
		return root;
	}

	public Parent createSelectMenu() throws IOException {
		root.getChildren().clear();
		addBackground("select-bg.gif");
		addTitleSelect();
		addLineSelect();
		herosSelect.setHgap(50);
		herosSelect.setVgap(40);
		addHerosSelect();
		addButtonSelect("Exit", 0);
		addButtonSelect("Back", 5.3);
		lineAnimationSelect();
		return root;
	}

	public Parent createGameplay() {
		buttons.getChildren().clear();
		root.getChildren().clear();
		addBackground("gameplay-bg3.jpg");
		addHerosGameplay();
		addButtonsGameplay();
		addTargetButtons();
		addTextGameplay();
		addMap();
		return root;
	}

	public Parent createEnd() {
		root.getChildren().clear();
		addBackground("credits-bg.gif");
		addTitleCredits();
		addLineCredits();
		addLine2Credits();
		addTextCredits("Mostafa El-Sewedy 55-5414", 0);
		addTextCredits("Mohamed Sweidan 55-5201", 1);
		addTextCredits("Mohamed Tobar 55-5914", 2);
		addButtonCredits("Exit", 3);
		lineAnimationCredits();
		if (Game.checkWin()) {
			addWinText();
		}
		else {
			addLostText();
		}
		return root;
	}

	public Parent createHowTo() {
		root.getChildren().clear();
		addBackground("howto-bg.png");
		addDataText();
		addTargetSelText();
		addButtonsText();
		addMapText();
		addMessageText();
		addTypesWalkthrough1();
		addTypesWalkthrough2();
		addTypesWalkthrough3();
		addVBoxText();
		addBackButton();
		return root;
	}

	public void addBackground(String s) {
		Image bg = new Image(s);
		ImageView bgview = new ImageView(bg);
		bgview.setFitHeight(720);
		bgview.setFitWidth(1280);
		root.getChildren().add(bgview);
	}

	public void addTitleStart() {
		Text ttl = new Text("The Last Of Us - Legacy");
		ttl.setX(440);
		ttl.setY(120);
		ttl.setFill(Color.WHITESMOKE);
		ttl.setFont(Font.font("Ariel", FontWeight.BOLD, 36));
		ttl.setEffect(new DropShadow(30, Color.BLACK));
		root.getChildren().add(ttl);
	}

	public void addTitleCredits() {
		Text ttl = new Text("The Last Of Us - Legacy");
		ttl.setX(440);
		ttl.setY(90);
		ttl.setFill(Color.WHITESMOKE);
		ttl.setFont(Font.font("Ariel", FontWeight.BOLD, 36));
		ttl.setEffect(new DropShadow(30, Color.BLACK));
		root.getChildren().add(ttl);
	}

	public void addTitleSelect() {
		Text ttl = new Text("Select your starting hero!");
		ttl.setX(425);
		ttl.setY(120);
		ttl.setFill(Color.WHITESMOKE);
		ttl.setFont(Font.font("Ariel", FontWeight.BOLD, 36));
		ttl.setEffect(new DropShadow(30, Color.BLACK));
		root.getChildren().add(ttl);
	}

	public void addLineStart() {
		line.setStartX(440);
		line.setEndX(440);
		line.setStartY(220);
		line.setEndY(500);
		line.setStrokeWidth(3);
		line.setStroke(Color.WHITESMOKE);
		line.setScaleY(0);
		root.getChildren().add(line);
	}

	public void addLineCredits() {
		lineCred.setStartX(440);
		lineCred.setEndX(440);
		lineCred.setStartY(220);
		lineCred.setEndY(500);
		lineCred.setStrokeWidth(3);
		lineCred.setStroke(Color.WHITESMOKE);
		lineCred.setScaleY(0);
		root.getChildren().add(lineCred);
	}

	public void addLine2Credits() {
		line2Cred.setStartX(840);
		line2Cred.setEndX(840);
		line2Cred.setStartY(220);
		line2Cred.setEndY(500);
		line2Cred.setStrokeWidth(3);
		line2Cred.setStroke(Color.WHITESMOKE);
		line2Cred.setScaleY(0);
		root.getChildren().add(line2Cred);
	}

	public void addLineSelect() {
		lineSelect.setStartX(320);
		lineSelect.setEndX(960);
		lineSelect.setStartY(180);
		lineSelect.setEndY(180);
		lineSelect.setStrokeWidth(3);
		lineSelect.setStroke(Color.WHITESMOKE);
		lineSelect.setScaleX(0);
		root.getChildren().add(lineSelect);
	}

	public void lineAnimationStart() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(2), line);
		st.setFromY(0);
		st.setToY(1);
		st.play();
	}

	public void lineAnimationCredits() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(2), lineCred);
		ScaleTransition st2 = new ScaleTransition(Duration.seconds(2), line2Cred);
		st.setFromY(0);
		st2.setFromY(0);
		st.setToY(1);
		st2.setToY(1);
		st.play();
		st2.play();
	}

	public void lineAnimationSelect() {
		ScaleTransition st = new ScaleTransition(Duration.seconds(2), lineSelect);
		st.setFromX(0);
		st.setToX(1);
		st.play();
	}

	public void addButtonStart(String n, double i) {
		Polygon bg = new Polygon(0, 0, 100, 0, 115, 15, 100, 30, 0, 30);
		bg.setFill(Color.color(0, 0, 0, 0.75));
		bg.setOpacity(0.5);
		bg.setTranslateX(line.getEndX() + 10);
		bg.setTranslateY(line.getStartY() + (i * 40));
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.25));
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.75));
			}
		});
		Button b = new Button(n);
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefSize(130, 30);
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (b.getText() == "Exit")
					Platform.exit();
				if (b.getText() == "Credits") {
					myStage.setScene(new Scene(createCredits()));
					myStage.setFullScreen(true);
				}
				if (b.getText() == "How-To") {
					myStage.setScene(new Scene(createHowTo()));
					myStage.setFullScreen(true);
				}
				if (b.getText() == "Single Player") {
					try {
						myStage.setScene(new Scene(createSelectMenu()));
						myStage.setFullScreen(true);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new DropShadow(5, Color.BLACK));
			}
		});
		b.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new BoxBlur(1, 1, 3));
			}
		});
		b.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.F))
					myStage.setFullScreen(true);
			}
		});
		b.setTranslateX(line.getStartX());
		b.setTranslateY(line.getStartY() + (i * 40));
		b.setBackground(null);
		b.setFont(Font.font(16));
		root.getChildren().addAll(bg, b);
	}

	public void addButtonCredits(String n, double i) {
		Polygon bg = new Polygon(-15, 15, 0, 0, 100, 0, 115, 15, 100, 30, 0, 30);
		bg.setFill(Color.color(0, 0, 0, 0.75));
		bg.setOpacity(0.5);
		bg.setTranslateX(lineCred.getEndX() + 140);
		bg.setTranslateY(lineCred.getStartY() + (i * 55) + 30);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.25));
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.75));
			}
		});
		Button b = new Button(n);
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefSize(130, 30);
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (b.getText() == "Exit")
					Platform.exit();
				if (b.getText() == "Back") {
					myStage.setScene(new Scene(createStart()));
					myStage.setFullScreen(true);
				}

			}
		});
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new DropShadow(5, Color.BLACK));
			}
		});
		b.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new BoxBlur(1, 1, 3));
			}
		});
		b.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.F))
					myStage.setFullScreen(true);
			}
		});
		b.setTranslateX(lineCred.getStartX() + 125);
		b.setTranslateY(lineCred.getStartY() + (i * 55) + 28);
		b.setBackground(null);
		b.setFont(Font.font(16));
		root.getChildren().addAll(bg, b);
	}

	public void addButtonSelect(String n, double i) {
		Polygon bg = new Polygon(-15, 15, 0, 0, 100, 0, 115, 15, 100, 30, 0, 30);
		bg.setFill(Color.color(0, 0, 0, 0.75));
		bg.setOpacity(0.5);
		bg.setTranslateX(lineSelect.getStartX() + (i * 100));
		bg.setTranslateY(lineSelect.getStartY() - 50);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.25));
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.75));
			}
		});
		Button b = new Button(n);
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefSize(130, 30);
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (b.getText() == "Exit")
					Platform.exit();
				if (b.getText() == "Back") {
					myStage.setScene(new Scene(createStart()));
					myStage.setFullScreen(true);
				}

			}
		});
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new DropShadow(5, Color.BLACK));
			}
		});
		b.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new BoxBlur(1, 1, 3));
			}
		});
		b.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.F))
					myStage.setFullScreen(true);
			}
		});
		b.setTranslateX(lineSelect.getStartX() + (i * 100) - 10);
		b.setTranslateY(lineSelect.getStartY() - 53);
		b.setBackground(null);
		b.setFont(Font.font(16));
		root.getChildren().addAll(bg, b);
	}

	public void addButtonGameplay(String n) {
		Pane p = new Pane();
		Polygon bg = new Polygon(0, 0, 135, 0, 150, 20, 135, 40, 0, 40);
		bg.setFill(Color.color(0, 0, 0, 0.75));
		bg.setOpacity(0.5);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.25));
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.75));
			}
		});
		Button b = new Button(n);
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefSize(130, 40);
		b.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode().equals(KeyCode.P)) {
					try {
						if (currHero.getTarget() != null) {
							if (currHero.getTarget().getCurrentHp() <= 0)
								currHero.setTarget(null);
						}
						currHero.attack();
						text.setText("You Dealt " + currHero.getAttackDmg() + " Damage");
						setData(currHero);
						updateMap();
					} catch (NotEnoughActionsException e) {
						text.setText(e.getMessage());
					} catch (InvalidTargetException e) {
						text.setText(e.getMessage());
					}
				}
				if (event.getCode().equals(KeyCode.C)) {
					try {
						currHero.cure();
						addHeroGameplay(Game.heroes.get(Game.heroes.size() - 1));
						updateMap();
						setData(currHero);
						text.setText("You Cured a Zombie!");
						if (Game.checkWin()) {
							myStage.setScene(new Scene(createEnd()));
							myStage.setFullScreen(true);
						}
					} catch (NoAvailableResourcesException e) {
						text.setText("You need a vaccine to be able to cure");
					} catch (InvalidTargetException e) {
						text.setText(e.getMessage());
					} catch (NotEnoughActionsException e) {
						text.setText(e.getMessage());
					}
				}
				if (event.getCode().equals(KeyCode.U)) {
					try {
						currHero.useSpecial();
						if(currHero instanceof Explorer)
							updateMap();
						setData(currHero);
					} catch (NoAvailableResourcesException e) {
						text.setText("You need a supply to be able to use ability");
					} catch (InvalidTargetException e) {
						text.setText(e.getMessage());
					}
				}
				if (event.getCode().equals(KeyCode.E)) {
					try {
						Game.endTurn();
						if (Game.checkGameOver() || Game.checkWin()) {
							myStage.setScene(new Scene(createEnd()));
							myStage.setFullScreen(true);
						}
						text.setText("");
						setData(currHero);
						updateMap();
						addHerosGameplay();
					} catch (NotEnoughActionsException ev) {
					} catch (InvalidTargetException ev) {
					}
				}
				if (event.getCode().equals(KeyCode.W)) {
					try {
						int x = currHero.getLocation().x;
						int y = currHero.getLocation().y;
						int chp = currHero.getCurrentHp();
						currHero.move(Direction.DOWN);
						if (currHero.getCurrentHp() < chp) {
							text.setText("You ran into a trap");
						}
						if (Game.checkGameOver() || Game.checkWin()) {
							myStage.setScene(new Scene(createEnd()));
							myStage.setFullScreen(true);
						}
						updateLocation(x, y);
					} catch (MovementException e) {
						text.setText(e.getMessage());
					} catch (NotEnoughActionsException e) {
						text.setText(e.getMessage());
					}
				}
				if (event.getCode().equals(KeyCode.S)) {
					try {
						int x = currHero.getLocation().x;
						int y = currHero.getLocation().y;
						int chp = currHero.getCurrentHp();
						currHero.move(Direction.UP);
						if (currHero.getCurrentHp() < chp) {
							text.setText("You ran into a trap");
						}
						if (Game.checkGameOver() || Game.checkWin()) {
							myStage.setScene(new Scene(createEnd()));
							myStage.setFullScreen(true);
						}
						updateLocation(x, y);
					} catch (MovementException e) {
						text.setText(e.getMessage());
					} catch (NotEnoughActionsException e) {
						text.setText(e.getMessage());
					}
				}
				if (event.getCode().equals(KeyCode.A)) {
					try {
						int x = currHero.getLocation().x;
						int y = currHero.getLocation().y;
						int chp = currHero.getCurrentHp();
						currHero.move(Direction.LEFT);
						if (currHero.getCurrentHp() < chp) {
							text.setText("You ran into a trap");
						}
						if (Game.checkGameOver() || Game.checkWin()) {
							myStage.setScene(new Scene(createEnd()));
							myStage.setFullScreen(true);
						}
						updateLocation(x, y);
					} catch (MovementException e) {
						text.setText(e.getMessage());
					} catch (NotEnoughActionsException e) {
						text.setText(e.getMessage());
					}
				}
				if (event.getCode().equals(KeyCode.D)) {
					try {
						int x = currHero.getLocation().x;
						int y = currHero.getLocation().y;
						int chp = currHero.getCurrentHp();
						currHero.move(Direction.RIGHT);
						if (currHero.getCurrentHp() < chp) {
							text.setText("You ran into a trap");
						}
						if (Game.checkGameOver() || Game.checkWin()) {
							myStage.setScene(new Scene(createEnd()));
							myStage.setFullScreen(true);
						}
						updateLocation(x, y);
					} catch (MovementException e) {
						text.setText(e.getMessage());
					} catch (NotEnoughActionsException e) {
						text.setText(e.getMessage());
					}
				}
				if(event.getCode().equals(KeyCode.F))
					myStage.setFullScreen(true);
			}

		});
		b.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if (b.getText() == "Exit") {
					Platform.exit();
				}
				if (b.getText() == "How-To") {
					myStage.setScene(new Scene(createHowTo()));
					myStage.setFullScreen(true);
				}
			}
		});
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new DropShadow(5, Color.BLACK));
			}
		});
		b.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new BoxBlur(1, 1, 3));
			}
		});
		b.setBackground(null);
		b.setFont(Font.font(18));
		p.getChildren().addAll(bg, b);
		buttons.getChildren().add(p);
	}

	public void addButtonsGameplay() {
		addButtonGameplay("Exit");
		addButtonGameplay("How-To");
		buttons.setTranslateX(450);
		buttons.setTranslateY(670);
		root.getChildren().add(buttons);
	}

	public void addTextCredits(String s, int i) {
		Text txt = new Text();
		txt.setText(s);
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 16));
		txt.setTranslateX(line.getStartX() + 100);
		txt.setTranslateY(line.getStartY() + (i * 60) + 30);
		root.getChildren().add(txt);
	}

	public void addTextGameplay() {
		text.setFill(Color.WHITE);
		text.setFont(Font.font(16));
		text.setEffect(new DropShadow(5, Color.BLACK));
		text.setTranslateX(910);
		text.setTranslateY(50);
		root.getChildren().add(text);
	}

	public void addHeroSelect(Hero h, int row, int column) {
		VBox datav = new VBox(10);
		addData(datav, h);
		Image himg = new Image("e1.png");
		if (h instanceof Fighter) {
			if (numF == 2)
				return;
			himg = new Image("f" + (++numF) + ".png");
			Text type = new Text("Fighter");
			type.setFill(Color.WHITE);
			type.setEffect(new DropShadow(5, Color.BLACK));
			datav.getChildren().add(type);
		}
		if (h instanceof Explorer) {
			if (numE == 3)
				return;
			himg = new Image("e" + (++numE) + ".png");
			Text type = new Text("Explorer");
			type.setFill(Color.WHITE);
			type.setEffect(new DropShadow(5, Color.BLACK));
			datav.getChildren().add(type);
		}
		if (h instanceof Medic) {
			if (numM == 3)
				return;
			himg = new Image("m" + (++numM) + ".png");
			Text type = new Text("Medic");
			type.setFill(Color.WHITE);
			type.setEffect(new DropShadow(5, Color.BLACK));
			datav.getChildren().add(type);
		}
		ImageView hview = new ImageView(himg);
		hview.setFitHeight(125);
		hview.setFitWidth(125);
		datav.setOpacity(0);
		hview.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hview.setOpacity(0);
				datav.setOpacity(1);
			}
		});
		hview.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hview.setOpacity(1);
				datav.setOpacity(0);
			}
		});
		hview.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 1) {
					datav.setEffect(new Glow(1));
					hview.setEffect(new Glow(1));
				}
				if (event.getClickCount() == 2) {
					Game.startGame(h);
					x = 1;
					myStage.setScene(new Scene(createGameplay()));
					myStage.setFullScreen(true);
				}
			}
		});
		herosSelect.add(datav, column, row);
		herosSelect.add(hview, column, row);
	}

	public void addHeroGameplay(Hero h) {
		Image himg = setIMG(h.getName());
		ImageView hview = new ImageView(himg);
		hview.setFitHeight(100);
		hview.setFitWidth(100);
		VBox data2 = new VBox();
		addDataGameplay(data2, h);
		Text type = new Text("");
		if (h instanceof Fighter) {
			type.setText("Fighter");
		}
		if (h instanceof Explorer) {
			type.setText("Explorer");
		}
		if (h instanceof Medic) {
			type.setText("Medic");
		}
		type.setFont(Font.font(10));
		type.setFill(Color.WHITE);
		type.setEffect(new DropShadow(5, Color.BLACK));
		data2.getChildren().add(type);
		data2.setOpacity(0);
		data2.setOnMouseEntered(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hview.setOpacity(0);
				data2.setOpacity(1);
			}
		});
		data2.setOnMouseExited(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				hview.setOpacity(1);
				data2.setOpacity(0);
			}
		});
		data2.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				currHero = h;
				setData(h);
			}
		});
		Pane tmp = new Pane();
		tmp.getChildren().addAll(hview, data2);
		herosGameplay.getChildren().add(tmp);
	}

	public void addData(VBox vb, Hero h) {
		Text name = new Text("Hero Name: " + h.getName());
		Text maxHP = new Text("Max HP: " + h.getMaxHp());
		Text maxDMG = new Text("Damage: " + h.getAttackDmg());
		Text maxActions = new Text("Max Actions: " + h.getMaxActions());
		name.setFill(Color.WHITE);
		maxHP.setFill(Color.WHITE);
		maxDMG.setFill(Color.WHITE);
		maxActions.setFill(Color.WHITE);
		name.setEffect(new DropShadow(5, Color.BLACK));
		maxHP.setEffect(new DropShadow(5, Color.BLACK));
		maxDMG.setEffect(new DropShadow(5, Color.BLACK));
		maxActions.setEffect(new DropShadow(5, Color.BLACK));
		vb.getChildren().add(name);
		vb.getChildren().add(maxHP);
		vb.getChildren().add(maxDMG);
		vb.getChildren().add(maxActions);
	}

	public void addDataGameplay(VBox vb, Hero h) {
		Text name = new Text("Hero Name: " + h.getName());
		Text maxHP = new Text("Max HP: " + h.getMaxHp());
		Text maxDMG = new Text("Damage: " + h.getAttackDmg());
		Text maxActions = new Text("Max Actions: " + h.getMaxActions());
		name.setFill(Color.WHITE);
		maxHP.setFill(Color.WHITE);
		maxDMG.setFill(Color.WHITE);
		maxActions.setFill(Color.WHITE);
		name.setEffect(new DropShadow(5, Color.BLACK));
		maxHP.setEffect(new DropShadow(5, Color.BLACK));
		maxDMG.setEffect(new DropShadow(5, Color.BLACK));
		maxActions.setEffect(new DropShadow(5, Color.BLACK));
		name.setFont(Font.font(10));
		maxHP.setFont(Font.font(10));
		maxDMG.setFont(Font.font(10));
		maxActions.setFont(Font.font(10));
		vb.getChildren().add(name);
		vb.getChildren().add(maxHP);
		vb.getChildren().add(maxDMG);
		vb.getChildren().add(maxActions);
	}

	public void addHerosSelect() throws IOException {
		Game.loadHeroes("Heroes.csv");
		ArrayList<Hero> h = Game.availableHeroes;
		int r = 0;
		int c = 0;
		for (int i = 0; i < h.size(); i++) {
			if (c > 3) {
				c = 0;
				r++;
			}
			addHeroSelect(h.get(i), r, c);
			c++;
		}
		herosSelect.setTranslateY(250);
		herosSelect.setTranslateX(310);
		root.getChildren().add(herosSelect);
	}

	public void addHerosGameplay() {
		data.getChildren().clear();
		herosGameplay.getChildren().clear();
		root.getChildren().remove(data);
		root.getChildren().remove(herosGameplay);
		for (int i = 0; i < Game.heroes.size(); i++) {
			addHeroGameplay(Game.heroes.get(i));
		}
		herosGameplay.setTranslateY(150);
		herosGameplay.setTranslateX(1150);
		data.setTranslateX(150);
		data.setTranslateY(20);
		currHero = Game.heroes.get(0);
		setData(currHero);
		root.getChildren().addAll(herosGameplay, data);
	}

	public void setData(Hero h) {
		data.getChildren().clear();
		Text name = new Text("Hero Name: " + h.getName());
		Text maxHP = new Text("HP: " + h.getCurrentHp());
		Text maxDMG = new Text("Damage: " + h.getAttackDmg());
		Text maxActions = new Text("Actions: " + h.getActionsAvailable());
		Text vaccines = new Text("Vaccines: " + h.getVaccineInventory().size());
		Text supplies = new Text("Supplies: " + h.getSupplyInventory().size());
		Text type = new Text();
		if (h instanceof Fighter)
			type.setText("Type: " + "Fighter");
		if (h instanceof Explorer)
			type.setText("Type: " + "Explorer");
		if (h instanceof Medic)
			type.setText("Type: " + "Medic");
		name.setFont(Font.font(14));
		maxHP.setFont(Font.font(14));
		maxDMG.setFont(Font.font(14));
		maxActions.setFont(Font.font(14));
		vaccines.setFont(Font.font(14));
		supplies.setFont(Font.font(14));
		type.setFont(Font.font(14));
		type.setFill(Color.WHITE);
		name.setFill(Color.WHITE);
		maxHP.setFill(Color.WHITE);
		maxDMG.setFill(Color.WHITE);
		maxActions.setFill(Color.WHITE);
		vaccines.setFill(Color.WHITE);
		supplies.setFill(Color.WHITE);
		name.setEffect(new DropShadow(5, Color.BLACK));
		maxHP.setEffect(new DropShadow(5, Color.BLACK));
		vaccines.setEffect(new DropShadow(5, Color.BLACK));
		supplies.setEffect(new DropShadow(5, Color.BLACK));
		maxDMG.setEffect(new DropShadow(5, Color.BLACK));
		maxActions.setEffect(new DropShadow(5, Color.BLACK));
		type.setEffect(new DropShadow(5, Color.BLACK));
		data.getChildren().add(name);
		data.getChildren().add(maxHP);
		data.getChildren().add(maxDMG);
		data.getChildren().add(maxActions);
		data.getChildren().add(vaccines);
		data.getChildren().add(supplies);
		data.getChildren().add(type);
	}

	public Image setIMG(String name) {
		Image img = new Image("e1.png");
		switch (name) {
		case "Joel Miller":
			img = new Image("f1.png");
			break;
		case "Ellie Williams":
			img = new Image("m1.png");
			break;
		case "Tess":
			img = new Image("e1.png");
			break;
		case "Riley Abel":
			img = new Image("e2.png");
			break;
		case "Tommy Miller":
			img = new Image("e3.png");
			break;
		case "Bill":
			img = new Image("m2.png");
			break;
		case "David":
			img = new Image("f2.png");
			break;
		case "Henry Burell":
			img = new Image("m3.png");
			break;
		}
		return img;
	}

	public void addTargetDownButton() {
		Polygon bg = new Polygon(0, 0, 0, 20, -15, 20, 7.5, 30, 30, 20, 15, 20, 15, 0);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setTranslateY(5);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x + 1;
				int y = currHero.getLocation().y;
				if (x > 15)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");

			}

		});
		moveButtons.add(bg, 1, 2);
	}

	public void addTargetSelfButton() {
		Polygon bg = new Polygon(10, 0, 10, 30, 50, 30, 50 , 0);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setTranslateX(3);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					int x = currHero.getLocation().x;
					int y = currHero.getLocation().y;
					if (Game.map[x][y] instanceof CharacterCell) {
						currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
						if (!(currHero.checkDistance())) {
							currHero.setTarget(null);
							text.setText("Target too far");
						}
					} else
						text.setText("Cannot set this as a target");
				}
			}

		});
		moveButtons.add(bg, 1, 1);
	}

	public void addTargetDownLeftButton() {
		Polygon bg = new Polygon(0, 0, 0, 20, -15, 20, 7.5, 30, 30, 20, 15, 20, 15, 0);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x + 1;
				int y = currHero.getLocation().y - 1;
				if (x > 15 || y < 0)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");
			}
		});
		bg.setRotate(45);
		moveButtons.add(bg, 0, 2);
	}

	public void addTargetDownRightButton() {
		Polygon bg = new Polygon(0, 0, 0, 20, -15, 20, 7.5, 30, 30, 20, 15, 20, 15, 0);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x + 1;
				int y = currHero.getLocation().y + 1;
				if (x > 15 || y < 0)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");
			}
		});
		bg.setRotate(-45);
		moveButtons.add(bg, 2, 2);
	}

	public void addTargetUpButton() {
		Polygon bg = new Polygon(0, 0, 0, -20, -15, -20, 7.5, -30, 30, -20, 15, -20, 15, 0);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setTranslateY(-5);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x - 1;
				int y = currHero.getLocation().y;
				if (x < 0)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");
			}
		});
		moveButtons.add(bg, 1, 0);
	}

	public void addTargetUpRightButton() {
		Polygon bg = new Polygon(0, 0, 0, -20, -15, -20, 7.5, -30, 30, -20, 15, -20, 15, 0);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x - 1;
				int y = currHero.getLocation().y + 1;
				if (x < 0)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");
			}
		});
		bg.setRotate(45);
		moveButtons.add(bg, 2, 0);
	}

	public void addTargetUpLeftButton() {
		Polygon bg = new Polygon(0, 0, 0, -20, -15, -20, 7.5, -30, 30, -20, 15, -20, 15, 0);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x - 1;
				int y = currHero.getLocation().y - 1;
				if (x < 0)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");
			}
		});
		bg.setRotate(-45);
		moveButtons.add(bg, 0, 0);
	}

	public void addTargetLeftButton() {
		Polygon bg = new Polygon(0, 0, -20, -0, -20, -15, -30, 7.5, -20, 30, -20, 15, 0, 15);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x;
				int y = currHero.getLocation().y - 1;
				if (y < 0)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");
			}
		});
		moveButtons.add(bg, 0, 1);
	}

	public void addTargetRightButton() {
		Polygon bg = new Polygon(0, 0, 20, 0, 20, -15, 30, 7.5, 20, 30, 20, 15, 0, 15);
		bg.setFill(Color.RED);
		bg.setOpacity(0.75);
		bg.setTranslateX(17);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.25);
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setOpacity(0.75);
			}
		});
		bg.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				int x = currHero.getLocation().x;
				int y = currHero.getLocation().y + 1;
				if (y > 15)
					return;
				if (Game.map[x][y] instanceof CharacterCell) {
					currHero.setTarget(((CharacterCell) Game.map[x][y]).getCharacter());
					if (!(currHero.checkDistance())) {
						currHero.setTarget(null);
						text.setText("Target too far");
					}
				} else
					text.setText("Cannot set this as a target");
			}
		});
		moveButtons.add(bg, 2, 1);
	}

	public void addTargetButtons() {
		addTargetSelfButton();
		addTargetUpButton();
		addTargetUpLeftButton();
		addTargetUpRightButton();
		addTargetDownButton();
		addTargetLeftButton();
		addTargetRightButton();
		addTargetDownLeftButton();
		addTargetDownRightButton();
		moveButtons.setTranslateX(100);
		moveButtons.setTranslateY(607.5);
		root.getChildren().add(moveButtons);
	}

	public void updateLocation(int oldx, int oldy) {
		setData(currHero);
		int x = currHero.getLocation().x;
		int y = currHero.getLocation().y;
		((ImageView)map.getChildren().get(oldx*15+oldy)).setImage(new Image("Hole.png"));
		((ImageView)map.getChildren().get(x*15+y)).setImage(setIMG(currHero.getName()));
		updateVisibility();

	}

	public void updateVisibility() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				if (Game.map[i][j].isVisible()) {
					ImageView b3 = (ImageView) map.getChildren().get(i * 15 + j);
					b3.setOpacity(1);
				}
			}
		}
	}

	public void addMap() {
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				Image img = new Image("Hole.png");
				ImageView imgv = new ImageView(img);
				if (Game.map[i][j] instanceof TrapCell) {
					img = new Image("Hole.png");
				}
				if (Game.map[i][j] instanceof CollectibleCell) {
					CollectibleCell cc = (CollectibleCell) Game.map[i][j];
					if (cc.getCollectible() instanceof Vaccine)
						img = new Image("Vaccine.png");
					else
						img = new Image("Supply.png");
				}
				if (Game.map[i][j] instanceof CharacterCell) {
					CharacterCell cc = (CharacterCell) Game.map[i][j];
					if (cc.getCharacter() == null) {
						img = new Image("Hole.png");
					} else if (cc.getCharacter() instanceof Zombie)
						img = new Image("z.png");
					else
						img = setIMG(cc.getCharacter().getName());
				}
				imgv = new ImageView(img);
				imgv.setFitHeight(34);
				imgv.setFitWidth(35);
				map.add(imgv, j, i);
				if (!(Game.map[i][j].isVisible())) {
					imgv.setOpacity(0);
				}
			}
		}
		map.setGridLinesVisible(true);
		map.setTranslateX(340);
		map.setTranslateY(105);
		root.getChildren().add(map);
	}

	public void updateMap() {
		map.getChildren().clear();
		for (int i = 0; i < 15; i++) {
			for (int j = 0; j < 15; j++) {
				Image img = new Image("Hole.png");
				ImageView imgv = new ImageView(img);
				if (Game.map[i][j] instanceof TrapCell) {
					img = new Image("Hole.png");
				}
				if (Game.map[i][j] instanceof CollectibleCell) {
					CollectibleCell cc = (CollectibleCell) Game.map[i][j];
					if (cc.getCollectible() instanceof Vaccine)
						img = new Image("Vaccine.png");
					else
						img = new Image("Supply.png");
				}
				if (Game.map[i][j] instanceof CharacterCell) {
					CharacterCell cc = (CharacterCell) Game.map[i][j];
					if (cc.getCharacter() == null) {
						img = new Image("Hole.png");
					} else if (cc.getCharacter() instanceof Zombie)
						img = new Image("z.png");
					else
						img = setIMG(cc.getCharacter().getName());
				}
				imgv = new ImageView(img);
				imgv.setFitHeight(34);
				imgv.setFitWidth(35);
				map.add(imgv, j, i);
				if (!(Game.map[i][j].isVisible())) {
					imgv.setOpacity(0);
				}
			}
		}
	}

	public void addWinText() {
		Text txt = new Text();
		txt.setText("You Won!!");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 24));
		txt.setTranslateX(line.getStartX() + 140);
		txt.setTranslateY(line.getStartY() + 300);
		root.getChildren().add(txt);
	}

	public void addLostText() {
		Text txt = new Text();
		txt.setText("You Lost!!");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 24));
		txt.setTranslateX(line.getStartX() + 140);
		txt.setTranslateY(line.getStartY() + 300);
		root.getChildren().add(txt);
	}

	public void addDataText() {
		Text txt = new Text();
		txt.setText("Here you see your current Hero's Data");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(450);
		txt.setTranslateY(50);
		root.getChildren().add(txt);
	}

	public void addTargetSelText() {
		Text txt = new Text();
		txt.setText("Select your target using these arrows");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(75);
		txt.setTranslateY(600);
		root.getChildren().add(txt);
	}

	public void addButtonsText() {
		Text txt = new Text();
		txt.setText("Use E for end turn, C for cure, U for use ability and P for attack");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(450);
		txt.setTranslateY(325);
		root.getChildren().add(txt);
	}

	public void addMapText() {
		Text txt = new Text();
		txt.setText("Here is your map, use AWSD to move");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(500);
		txt.setTranslateY(300);
		root.getChildren().add(txt);
	}

	public void addMessageText() {
		Text txt = new Text();
		txt.setText("Here you will get messages for invalid actions");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(1000);
		txt.setTranslateY(60);
		root.getChildren().add(txt);
	}

	public void addTypesWalkthrough1() {
		Text txt = new Text();
		txt.setText("Your Hero can be a medic, which can use his ability to heal back HP");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(450);
		txt.setTranslateY(400);
		root.getChildren().add(txt);
	}

	public void addTypesWalkthrough2() {
		Text txt = new Text();
		txt.setText("Your Hero can be an explorer, which can use his ability to make the whole map visible");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(425);
		txt.setTranslateY(425);
		root.getChildren().add(txt);
	}

	public void addTypesWalkthrough3() {
		Text txt = new Text();
		txt.setText("Or Your Hero can be a fighter, which can use his ability to attack without any action points");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(425);
		txt.setTranslateY(450);
		root.getChildren().add(txt);
	}

	public void addVBoxText() {
		Text txt = new Text();
		txt.setText("Here you can find heros you have unlocked, click to switch!!");
		txt.setFill(Color.WHITE);
		txt.setFont(Font.font("Ariel", FontWeight.BOLD, 10));
		txt.setTranslateX(971);
		txt.setTranslateY(140);
		root.getChildren().add(txt);
	}

	public void addBackButton() {
		Polygon bg = new Polygon(-15, 15, 0, 0, 100, 0, 115, 15, 100, 30, 0, 30);
		bg.setFill(Color.color(0, 0, 0, 0.75));
		bg.setOpacity(0.5);
		bg.setTranslateX(780);
		bg.setTranslateY(676);
		bg.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.25));
			}
		});
		bg.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setFill(Color.color(0, 0, 0, 0.75));
			}
		});
		Button b = new Button("Back");
		b.setTextFill(Color.WHITESMOKE);
		b.setPrefSize(130, 30);
		b.setTranslateX(760);
		b.setTranslateY(675);
		b.setBackground(null);
		b.setFont(Font.font(16));
		b.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new DropShadow(5, Color.BLACK));
			}
		});
		b.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				bg.setEffect(new BoxBlur(1, 1, 3));
			}
		});
		b.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent e) {
				if (x == 0)
					myStage.setScene(new Scene(createStart()));
				if (x == 1)
					myStage.setScene(new Scene(createGameplay()));
				myStage.setFullScreen(true);
			}
		});
		b.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {
				if(e.getCode().equals(KeyCode.F))
					myStage.setFullScreen(true);
			}
		});
		root.getChildren().addAll(bg, b);
	}

	@Override
	public void start(Stage stage) throws Exception {
		Scene scene = new Scene(createStart());
		myStage.setScene(scene);
		myStage.setTitle("The Last Of Us - Legacy");
		myStage.getIcons().add(new Image("icon.png"));
		myStage.setFullScreen(true);
		myStage.setFullScreenExitHint("Press ESC to exit FullScreen, F to return to FullScreen");
		myStage.setResizable(false);
		myStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

}
