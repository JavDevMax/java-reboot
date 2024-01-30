package ru.sberbank.edu.repository;


import ru.sberbank.edu.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CarDbRepositoryImpl implements CarRepository {
    private final Connection connection;
    private static final String CREATE_CAR_SQL = "INSERT INTO car (id, model) VALUES (?,?)";
    private static final String UPDATE_CAR_SQL = "UPDATE car SET model = ? WHERE id = ?";
    private static final String SELECT_CAR_BY_ID = "SELECT * FROM car WHERE id = ?";
    private static final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
    private static final String DELETE_ALL_CAR = "DELETE FROM car";
    private static final String DELETE_CAR_BY_ID = "DELETE FROM car WHERE id = ?";
    private static final String FIND_ALL_CAR = "SELECT * FROM car";
    private final PreparedStatement createPreStmt;
    private final PreparedStatement updatePreStmt;
    private final PreparedStatement findByIdPreStmt;
    private final PreparedStatement deleteAll;
    private final PreparedStatement deleteById;
    private final PreparedStatement findAll;
    private final PreparedStatement findByModel;

    public CarDbRepositoryImpl(Connection connection) throws SQLException {
        this.connection = connection;
        this.createPreStmt = connection.prepareStatement(CREATE_CAR_SQL);
        this.updatePreStmt = connection.prepareStatement(UPDATE_CAR_SQL);
        this.findByIdPreStmt = connection.prepareStatement(SELECT_CAR_BY_ID);
        this.deleteAll = connection.prepareStatement(DELETE_ALL_CAR);
        this.deleteById = connection.prepareStatement(DELETE_CAR_BY_ID);
        this.findAll = connection.prepareStatement(FIND_ALL_CAR);
        this.findByModel = connection.prepareStatement(SELECT_CAR_BY_MODEL);
    }

    @Override
    public Car createOrUpdate(Car car) throws SQLException {
        Optional<Car> optCar = findById(car.getId());
        if (optCar.isEmpty()) {
            createPreStmt.setString(1, car.getId());
            createPreStmt.setString(2, car.getModel());
            createPreStmt.executeUpdate();
        } else {
            updatePreStmt.setString(1, car.getModel());
            updatePreStmt.setString(2, car.getId());
            updatePreStmt.executeUpdate();
        }
        return car;
    }

    /**
     * @param cars
     * @return
     */
    @Override
    public Set<Car> createAll(Collection<Car> cars) {
        Set<Car> carsSet = new HashSet<>(cars);
        if (carsSet.size() != cars.size()) {
            throw new IllegalArgumentException("Collection of cars has duplicates by ID ");
        }
        carsSet.forEach(car -> {
            try {
                createOrUpdate(car);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
        return carsSet;
    }

    /**
     * @return
     */
    @Override
    public Set<Car> findAll() {
        try {
            ResultSet resultSet = this.findAll.executeQuery();
            Set<Car> cars = new HashSet<>();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Method findAll threw an exception");
        }
        return null;
    }

    @Override
    public Optional<Car> findById(String id) throws SQLException {
        // validation
        int rowsCount = countRowsById(id);
        if (rowsCount > 1) {
            throw new RuntimeException("Car with id = " + id + " was found many times (" + rowsCount + ").");
        } else if (rowsCount == 0) {
            return Optional.empty();
        }

        findByIdPreStmt.setString(1, id);
        ResultSet resultSet = findByIdPreStmt.executeQuery();

        resultSet.next();
        Car car = new Car(resultSet.getString(1), resultSet.getString(2));
        return Optional.of(car);
    }

    @Override
    public Boolean deleteById(String id) {
        try {
            this.deleteById.setString(1, id);
            int delete_row = this.deleteById.executeUpdate();
            return delete_row != 0;
        } catch (SQLException e) {
            System.out.println("Method deleteById threw an exception");
        }
        return null;
    }

    /**
     * @return
     */
    @Override
    public Boolean deleteAll() {
        try {
            int delete_row = deleteAll.executeUpdate();
            return delete_row != 0;
        } catch (SQLException e) {
            System.out.println("Method deleteAll threw an exception");
        }
        return null;
    }


    private int countRowsById(String id) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM car where id = ?");
        preparedStatement.setString(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        int rowCount = 0;
        while (resultSet.next()) {
            rowCount = resultSet.getInt(1);
        }
        return rowCount;
    }

    /**
     * @param model
     * @return
     */
    @Override
    public Set<Car> findByModel(String model) {
        try {
            Set<Car> cars = new HashSet<>();
            findByModel.setString(1, model);
            ResultSet resultSet = findByModel.executeQuery();
            while (resultSet.next()) {
                cars.add(new Car(resultSet.getString(1), resultSet.getString(2)));
            }
            return cars;
        } catch (SQLException e) {
            System.out.println("Method findByModel threw an exception");
        }
        return null;
    }
}
