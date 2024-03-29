package patterns.visitor;

public class Notification implements Insurance.Visitor {

    void notify(Insurance insurance) {
        insurance.accept(this);
    }

    @Override
    public void visitCarInsurance(Insurance.Car car) {
        System.out.println("Email is sent to element class: " + car.getClass());
    }

    @Override
    public void visitMotorBikeInsurance(Insurance.MotorBike motorBike) {
        System.out.println("SMS is sent to element class: " + motorBike.getClass());
    }
}
