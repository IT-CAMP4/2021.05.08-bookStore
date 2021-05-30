package pl.camp.it.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.camp.it.dao.IOrderPositionDAO;
import pl.camp.it.model.BasketPosition;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component
public class OrderPositionDAO implements IOrderPositionDAO {

    @Autowired
    Connection connection;

    public void addOrderPosition(BasketPosition position, int orderId) {
        try {
            String sql = "INSERT INTO torderposition (pieces, book_id, order_id) VALUES (?, ?, ?);";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, position.getPieces());
            preparedStatement.setInt(2, position.getBook().getId());
            preparedStatement.setInt(3, orderId);

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<BasketPosition> getOrderPositionsForOrder(int orderId) {
        List<BasketPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            preparedStatement.setInt(1, orderId);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                BasketPosition basketPosition = new BasketPosition();
                basketPosition.setId(rs.getInt("id"));
                basketPosition.setPieces(rs.getInt("pieces"));
                basketPosition.setBookId(rs.getInt("book_id"));

                result.add(basketPosition);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
