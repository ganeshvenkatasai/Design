
class TV {

    private int volume;

    public void turnOn() {
        System.out.println("TV is ON");
    }

    public void turnOff() {
        System.out.println("TV is OFF");
    }

    public void volumeUp() {
        volume++;
        System.out.println("Volume is increased to : " + volume);
    }

    public void volumeDown() {
        volume--;
        System.out.println("Volume is decreased to : " + volume);
    }
}


interface Command  {
    void execute();
}


class TurnOnCommand implements Command {

    private TV tv;

    public TurnOnCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}

class TurnOffCommand implements Command {

    private TV tv;

    public TurnOffCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}

class VolumeUpCommand implements Command {

    private TV tv;

    public VolumeUpCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.volumeUp();
    }
}

class VolumeDownCommand implements Command {

    private TV tv;

    public VolumeDownCommand(TV tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.volumeDown();
    }
}


class RemoteControl {

    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}



public class Main {
    public static void main(String[] args) {
        TV tv = new TV();
        Command turnOn = new TurnOnCommand(tv);
        Command turnOff = new TurnOffCommand(tv);
        Command volumeUpCommand = new VolumeUpCommand(tv);
        Command volumeDownCommand = new VolumeDownCommand(tv);
        RemoteControl remoteControl = new RemoteControl();

        remoteControl.setCommand(turnOn);
        remoteControl.pressButton();

        remoteControl.setCommand(volumeUpCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(volumeDownCommand);
        remoteControl.pressButton();

        remoteControl.setCommand(turnOff);
        remoteControl.pressButton();

    }
}
