

abstract class LeaveHandler {
    protected LeaveHandler nextHandler;

    abstract void handleRequest(int days);

    void setNextHandler(LeaveHandler nextHandler) {
        this.nextHandler = nextHandler;
    }
}

class TeamLead extends LeaveHandler{
    @Override
    void handleRequest(int days) {
        if (days < 3) {
            System.out.println("Team Lead approved " + days + " days Leave");
        } else {
            nextHandler.handleRequest(days);
        }
    }
}

class Manager extends LeaveHandler{
    @Override
    void handleRequest(int days) {
        if (days < 8) {
            System.out.println("Manager approved " + days + " days Leave");
        } else {
            nextHandler.handleRequest(days);
        }
    }
}

class CEO extends LeaveHandler{
    @Override
    void handleRequest(int days) {
        System.out.println("CEO approved " + days + " days Leave");
    }
}


public class Main {
    public static void main(String[] args) {
        LeaveHandler teamLead = new TeamLead();
        LeaveHandler manager = new Manager();
        LeaveHandler ceo = new CEO();

        teamLead.setNextHandler(manager);
        manager.setNextHandler(ceo);

        teamLead.handleRequest(2);
        teamLead.handleRequest(7);
        teamLead.handleRequest(30);

    }
}
