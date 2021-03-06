package nju.quadra.hms.ui.mainUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import nju.quadra.hms.net.HttpClient;
import nju.quadra.hms.ui.common.Dialogs;
import nju.quadra.hms.ui.customerUI.CustomerNavigation;
import nju.quadra.hms.ui.hotelStaffUI.HotelStaffNavigation;
import nju.quadra.hms.ui.loginUI.LoginView;
import nju.quadra.hms.ui.webMarketerUI.WebMarketerNavigation;
import nju.quadra.hms.ui.webmasterUI.WebmasterNavigation;
import nju.quadra.hms.util.ClientConfig;

import java.io.IOException;
import java.util.Optional;

public class MainView extends Stage {

    @FXML
    private Label labelUsername, labelUserType;
    @FXML
    private Pane navPane, contentPane;
    @FXML
    private Button btnLotout;

    public MainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main.fxml"));
        loader.setController(this);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        this.initStyle(StageStyle.TRANSPARENT);
        this.setScene(scene);
        this.setResizable(false);

        if (HttpClient.session != null) {
            labelUsername.setText(HttpClient.session.username);
            labelUserType.setText(HttpClient.session.userType.toString());
            navPane.getChildren().clear();
            switch (HttpClient.session.userType) {
                case CUSTOMER:
                    navPane.getChildren().add(new CustomerNavigation(this));
                    break;
                case HOTEL_STAFF:
                    navPane.getChildren().add(new HotelStaffNavigation(this));
                    break;
                case WEBSITE_MARKETER:
                    navPane.getChildren().add(new WebMarketerNavigation(this));
                    break;
                case WEBSITE_MASTER:
                    navPane.getChildren().add(new WebmasterNavigation(this));
                    break;
            }
        }
    }

    public void loadView(Node node) {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(node);
    }

    public void loadViewOverlay(Node node) {
        contentPane.getChildren().clear();
        contentPane.getChildren().add(node);
    }

    /**
     * 提供窗口拖放支持
     */
    private double offsetX, offsetY;

    @FXML
    protected void onDragStart(MouseEvent event) {
        this.offsetX = event.getSceneX();
        this.offsetY = event.getSceneY();
    }

    @FXML
    protected void onDrag(MouseEvent event) {
        this.setX(event.getScreenX() - this.offsetX);
        this.setY(event.getScreenY() - this.offsetY);
    }

    @FXML
    protected void onLogoutAction() throws Exception {
        Dialogs.showInfo("登出成功！");
        ClientConfig.getConfig().setUsername("");
        ClientConfig.getConfig().setPassword("");
        LoginView loginView = new LoginView(HttpClient.session.username);
        HttpClient.session = null;
        loginView.show();
        this.close();
    }

    @FXML
    protected void onExitAction() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认");
        alert.setHeaderText(null);
        alert.setContentText("确认退出酒店管理系统?");
        alert.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);
        Optional<ButtonType> confirm = alert.showAndWait();
        if (confirm.isPresent() && confirm.get().equals(ButtonType.YES)) {
            System.exit(0);
        }
    }

}
