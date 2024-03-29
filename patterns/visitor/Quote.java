package patterns.visitor;

class Quote implements Insurance.Visitor {

    void print(Insurance insurance) {
        insurance.accept(this);
    }

    @Override
    public void visitCarInsurance(Insurance.Car car) {
        System.out.println("Get quote for a car model: " + car.model);
    }

    @Override
    public void visitMotorBikeInsurance(Insurance.MotorBike motorBike) {
        System.out.println("Get quote for a Motorbike model: " + motorBike.model);
    }
}
