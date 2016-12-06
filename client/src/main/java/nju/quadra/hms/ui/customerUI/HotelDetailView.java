package nju.quadra.hms.ui.customerUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import nju.quadra.hms.controller.CustomerController;
import nju.quadra.hms.vo.HotelRoomVO;
import nju.quadra.hms.vo.HotelSearchVO;

import java.io.IOException;

/**
 * Created by RaUkonn on 2016/12/5.
 */
public class HotelDetailView extends Parent {

    private HotelSearchView parent;
    private CustomerController controller = new CustomerController();
    private HotelSearchVO vo;

    public HotelDetailView(HotelSearchView parent, HotelSearchVO vo) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hoteldetail.fxml"));
        loader.setController(this);
        this.getChildren().add(loader.load());

        this.parent = parent;
        this.vo = vo;

        if (vo != null) {
            labelHotelName.setText(vo.name);
            labelAddress.setText(vo.address);
            labelDescription.setText(vo.description);
            labelFacilities.setText(vo.facilities);
            String rooms = "";
            for (HotelRoomVO room : vo.rooms) {
                rooms += room.toString();
            }
            labelRooms.setText(rooms);
        }
    }

    @FXML
    private Label labelHotelName, labelAddress, labelDescription, labelFacilities, labelRooms, labelComment;
    @FXML
    private ScrollPane scrollComment, scrollHistory;

    @FXML
    public void onCancelAction() {
        this.getChildren().clear();
    }

    @FXML
    public void onOrderAction() throws IOException{
        //parent.loadView(new BookHotelView(vo.id));
    }
}
