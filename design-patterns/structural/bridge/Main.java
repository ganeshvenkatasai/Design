// Step 1: Implementor Interface (Device)
interface Device {
    void turnOn();
    void turnOff();
    void setVolume(int percent);
}

// Step 2: Concrete Implementations (TV and Radio)
class TV implements Device {
    @Override
    public void turnOn() {
        System.out.println("TV is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("TV is turned OFF");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("TV volume set to " + percent + "%");
    }
}

class Radio implements Device {
    @Override
    public void turnOn() {
        System.out.println("Radio is turned ON");
    }

    @Override
    public void turnOff() {
        System.out.println("Radio is turned OFF");
    }

    @Override
    public void setVolume(int percent) {
        System.out.println("Radio volume set to " + percent + "%");
    }
}

// Step 3: Abstraction (RemoteControl)
abstract class RemoteControl {
    protected Device device;

    public RemoteControl(Device device) {
        this.device = device;
    }

    public void turnOn() {
        device.turnOn();
    }

    public void turnOff() {
        device.turnOff();
    }

    public abstract void increaseVolume();
}

// Step 4: Refined Abstraction (Basic and Advanced Remotes)
class BasicRemote extends RemoteControl {
    public BasicRemote(Device device) {
        super(device);
    }

    @Override
    public void increaseVolume() {
        System.out.println("Basic Remote: Increasing volume");
        device.setVolume(50); // Example static volume
    }
}

class AdvancedRemote extends RemoteControl {
    public AdvancedRemote(Device device) {
        super(device);
    }

    @Override
    public void increaseVolume() {
        System.out.println("Advanced Remote: Increasing volume");
        device.setVolume(75); // Higher volume setting
    }

    public void mute() {
        System.out.println("Advanced Remote: Muting device");
        device.setVolume(0);
    }
}

// Step 5: Main Class
public class Main {
    public static void main(String[] args) {
        // Use a Basic Remote with a TV
        Device tv = new TV();
        RemoteControl basicRemote = new BasicRemote(tv);

        System.out.println("Using Basic Remote with TV:");
        basicRemote.turnOn();
        basicRemote.increaseVolume();
        basicRemote.turnOff();

        System.out.println("\n----------------------\n");

        // Use an Advanced Remote with a Radio
        Device radio = new Radio();
        AdvancedRemote advancedRemote = new AdvancedRemote(radio);

        System.out.println("Using Advanced Remote with Radio:");
        advancedRemote.turnOn();
        advancedRemote.increaseVolume();
        advancedRemote.mute();
        advancedRemote.turnOff();
    }
}
