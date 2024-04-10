package client;


import javax.jws.WebParam;

public interface CommonServerImplInterface {
    public String getServerName();

    public String listAppointmentAvailability(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0);

    public String getOthersAvailability(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0);

    public String addAppointment(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0,
            @WebParam(name = "arg1", partName = "arg1")
            AppointmentType arg1,
            @WebParam(name = "arg2", partName = "arg2")
            int arg2);

    public String bookAppointment(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0,
            @WebParam(name = "arg1", partName = "arg1")
            AppointmentType arg1,
            @WebParam(name = "arg2", partName = "arg2")
            String arg2);

    public String getAppointmentSchedule(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0);

    public String cancelAppointment(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0,
            @WebParam(name = "arg1", partName = "arg1")
            String arg1);

    public String removeAppointment(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0,
            @WebParam(name = "arg1", partName = "arg1")
            AppointmentType arg1);

    public String swapAppointment(
            @WebParam(name = "arg0", partName = "arg0")
            String arg0,
            @WebParam(name = "arg1", partName = "arg1")
            String arg1,
            @WebParam(name = "arg2", partName = "arg2")
            String arg2,
            @WebParam(name = "arg3", partName = "arg3")
            String arg3,
            @WebParam(name = "arg4", partName = "arg4")
            String arg4);
}
