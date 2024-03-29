package patterns.visitor;

abstract class Insurance {
    interface Visitor {
        void visitCarInsurance(Car car);

        void visitMotorBikeInsurance(MotorBike motorBike);
    }

    abstract void accept(Visitor visitor);

    static class Car extends Insurance {
        Car(String registrationNumber,
                String postCode,
                String make,
                String model,
                int engineCC,
                boolean isLeftHandDrive,
                boolean isModified) {
            this.registrationNumber = registrationNumber;
            this.postCode = postCode;
            this.make = make;
            this.model = model;
            this.engineCC = engineCC;
            this.isLeftHandDrive = isLeftHandDrive;
            this.isModified = isModified;
        }

        final String registrationNumber;
        final String postCode;
        final String make;
        final String model;
        final int engineCC;
        final boolean isLeftHandDrive;
        final boolean isModified;

        @Override
        void accept(Visitor visitor) {
            visitor.visitCarInsurance(this);
        }
    }

    static class MotorBike extends Insurance {
        MotorBike(
                String registrationNumber,
                String postCode,
                String make,
                String model,
                int engineCC) {

            this.registrationNumber = registrationNumber;
            this.postCode = postCode;
            this.make = make;
            this.model = model;
            this.engineCC = engineCC;
        }

        final String registrationNumber;
        final String postCode;
        final String make;
        final String model;
        final int engineCC;

        @Override
        void accept(Visitor visitor) {
            visitor.visitMotorBikeInsurance(this);
        }
    }

}
