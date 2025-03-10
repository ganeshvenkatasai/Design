// Step 1: Target Interface (Type-C Charger)
interface TypeCCharger {
    void chargeWithTypeC();
}

// Step 2: Adaptee (Micro-USB Charger)
class MicroUSBCharger {
    public void chargeWithMicroUSB() {
        System.out.println("Charging with Micro-USB Charger...");
    }
}

// Step 3: Adapter Class (Converts Micro-USB to Type-C)
class TypeCAdapter implements TypeCCharger {
    private MicroUSBCharger microUSBCharger;

    public TypeCAdapter(MicroUSBCharger microUSBCharger) {
        this.microUSBCharger = microUSBCharger;
    }

    @Override
    public void chargeWithTypeC() {
        System.out.println("Using Adapter: Converting Type-C to Micro-USB...");
        microUSBCharger.chargeWithMicroUSB();
    }
}

// Step 4: Client (Mobile that requires Type-C Charger)
class Mobile {
    private TypeCCharger charger;

    public Mobile(TypeCCharger charger) {
        this.charger = charger;
    }

    public void charge() {
        charger.chargeWithTypeC();
    }
}

// Step 5: Main class
public class Main {
    public static void main(String[] args) {
        // We have a Micro-USB charger
        MicroUSBCharger microUSBCharger = new MicroUSBCharger();

        // Use an adapter to connect Micro-USB charger to Type-C port
        TypeCCharger adapter = new TypeCAdapter(microUSBCharger);

        // Mobile expects Type-C charger, but we use an adapter
        Mobile mobile = new Mobile(adapter);

        // Charge the mobile
        mobile.charge();
    }
}
