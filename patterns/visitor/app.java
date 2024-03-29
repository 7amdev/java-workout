package patterns.visitor;

public class app {
    public static void main(String[] args) {
        Insurance carInsurance = new Insurance.Car("BH-1212", "8298-12", "Mercedes", "G78", 829, false, false);
        Insurance motorBikeInsurance = new Insurance.MotorBike("AS-1234", "7651-278", "Audi", "X7", 1234);

        Quote quote = new Quote();
        quote.print(motorBikeInsurance);
        quote.print(carInsurance);

        Notification notification = new Notification();
        notification.notify(carInsurance);

    }

}
