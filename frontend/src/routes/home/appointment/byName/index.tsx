import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import { toast } from "sonner";
import {
  CalendarIcon,
  Edit,
  Search,
  ToggleLeft,
  ToggleRight,
  Trash,
} from "lucide-react";

import {
  AlertDialog,
  AlertDialogAction,
  AlertDialogCancel,
  AlertDialogContent,
  AlertDialogFooter,
  AlertDialogHeader,
  AlertDialogTitle,
  AlertDialogTrigger,
} from "@/components/ui/alert-dialog";
import { Button } from "@/components/ui/button";

import {
  Table,
  TableBody,
  TableCaption,
  TableCell,
  TableHead,
  TableHeader,
  TableRow,
} from "@/components/ui/table";
import { API_URL } from "@/config/config";
import { cn, format } from "@/lib/utils";
import { Input } from "@/components/ui/input";

interface Data {
  appointmentId: number;
  appointmentDate: String;
  appointmentTime: String;
  patient: {
    firstName: String;
    lastName: String;
    mobile: number;
  };
  status: String;
  regFeeStatus: String;
}

interface TreatmentType {
  id: number;
  treatmentTypeName: string;
  treatmentTypePrice: number;
}

const AppointmentsByName: React.FC = () => {
  const [data, setData] = useState<Data[]>([]);
  const [treatmentTypes, setTreatmentTypes] = useState<TreatmentType[]>([]);
  const [checkedIds, setCheckedIds] = useState<number[]>([]);
  const [patientName, setPatientName] = React.useState<string>("");

  const handleCheckboxChange = (treatmentTypeId: number) => {
    setCheckedIds((prevCheckedIds) => {
      if (prevCheckedIds.includes(treatmentTypeId)) {
        // If the checkbox is already checked, remove its ID
        return prevCheckedIds.filter((id) => id !== treatmentTypeId);
      } else {
        // If the checkbox is not checked, add its ID
        return [...prevCheckedIds, treatmentTypeId];
      }
    });
  };

  useEffect(() => {
    const fetchData = async () => {
      try {
        // appointments
        const appointmentResponse = await fetch(API_URL + "/appointments");
        if (!appointmentResponse.ok) {
          toast.error("Something went wrong");
        }

        const appointments = await appointmentResponse.json();
        setData(appointments);

        // treatment types
        const treatmentTypesResponse = await fetch(
          API_URL + "/treatment-types"
        );
        if (!treatmentTypesResponse.ok) {
          toast.error("Something went wrong");
        }

        const treatmentTypes = await treatmentTypesResponse.json();
        setTreatmentTypes(treatmentTypes);
      } catch (error) {
        console.log(error);
        toast.error("Error occurred when data fetching");
      }
    };

    fetchData();
  }, []);

  const handleDelete = async (appointmentId: number) => {
    await fetch(API_URL + "/appointments/" + appointmentId, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(() => {
        toast.error("Appointment deleted successfully");
        const redirectTo = () => {
          window.location.href = "/appointments/by-name";
        };
        setTimeout(redirectTo, 1000);
      })
      .catch((error) => {
        console.error("Error:", error);
        toast.error("Something went wrong");
      });
  };

  const handleAppointmentStatus = async (appointmentId: number) => {
    const response = await fetch(API_URL + "/appointments/" + appointmentId);
    if (!response.ok) {
      toast.error("Something went wrong");
    }

    const appointment = await response.json();

    if (appointment && appointment.status === "PENDING") {
      appointment.status = "COMPLETE";
    } else if (appointment && appointment.status === "COMPLETE") {
      appointment.status = "PENDING";
    }

    delete appointment.appointmentId;
    // update appointment status
    const updatedAppointmentResponse = await fetch(
      API_URL + "/appointments/" + appointmentId,
      {
        method: "PUT",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(appointment),
      }
    );
    if (!updatedAppointmentResponse.ok) {
      toast.error("Something went wrong");
    }
    toast.success("Appointment updated successfully");

    const updatedAppointment = await updatedAppointmentResponse.json();

    if (checkedIds.length > 0) {
      // get selected treatment types
      const selectedTreatmentTypesResponse = await fetch(
        API_URL + "/treatment-types/selected",
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(checkedIds),
        }
      );
      if (!selectedTreatmentTypesResponse.ok) {
        toast.error("Something went wrong");
      }

      const selectedTreatmentTypes = await selectedTreatmentTypesResponse.json();

      // create treatment
      const treatmentResponse = await fetch(API_URL + "/treatments", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          appointment: updatedAppointment,
          treatmentTypes: selectedTreatmentTypes,
        }),
      });
      if (!treatmentResponse.ok) {
        toast.error("Something went wrong");
      }
      const treatment = await treatmentResponse.json();

      // create invoice
      const invoicesResponse = await fetch(API_URL + "/invoices", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          treatmentId: treatment.treatmentId,
          appointment: updatedAppointment,
          treatmentTypes: selectedTreatmentTypes
        }),
      });
      if (!invoicesResponse.ok) {
        toast.error("Something went wrong");
      }

      const invoice = await invoicesResponse.json();
      window.location.href = "/invoices/view/" + invoice.invoiceId;
    }
    const redirectTo = () => {
      window.location.href = "/appointments/by-name";
    };
    setTimeout(redirectTo, 1000);
  };

  const handleRegFeeStatus = async (appointmentId: number) => {
    const response = await fetch(API_URL + "/appointments/" + appointmentId);
    if (!response.ok) {
      toast.error("Something went wrong");
    }

    const appointment = await response.json();

    if (appointment && appointment.regFeeStatus === "PENDING") {
      appointment.regFeeStatus = "PAID";
    } else if (appointment && appointment.regFeeStatus === "PAID") {
      appointment.regFeeStatus = "PENDING";
    }

    await fetch(API_URL + "/appointments/" + appointmentId, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(appointment),
    })
      .then((response) => {
        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }
        return response.json();
      })
      .then(() => {
        toast.error("Appointment updated successfully");
        const redirectTo = () => {
          window.location.href = "/appointments/by-name";
        };
        setTimeout(redirectTo, 1000);
      })
      .catch((error) => {
        console.error("Error:", error);
        toast.error("Something went wrong");
      });
  };

  const handleEdit = async (appointmentId: number) => {
    window.location.href = "appointments/update/" + appointmentId;
  };

  const loadDataByDate = async () => {
    try {
      // appointments
      const appointmentResponse = await fetch(
        API_URL + "/appointments/by-name?name=" + patientName
      );
      if (!appointmentResponse.ok) {
        toast.error("Something went wrong");
      }

      try {
        const appointment = await appointmentResponse.json();
        setData(appointment);
      } catch (error) {
        toast.info("No appointments for the searched user");
      }

      // treatment types
      const treatmentTypesResponse = await fetch(API_URL + "/treatment-types");
      if (!treatmentTypesResponse.ok) {
        toast.error("Something went wrong");
      }

      const treatmentTypes = await treatmentTypesResponse.json();
      setTreatmentTypes(treatmentTypes);
    } catch (error) {
      console.log(error);
      toast.error("Error occurred when data fetching");
    }
  };

  const handleInputChange = (event: React.ChangeEvent<HTMLInputElement>) => {
    setPatientName(event.target.value);
  };

  return (
    <div className="w-screen px-28 space-y-10">
      <div className="flex justify-between items-center">
        <h2 className="text-center text-2xl font-semibold">
          Filter Appointments By Patient Name
        </h2>
        <div className="space-x-2">
          <Link to="/appointments">
            <Button className="uppercase">Back</Button>
          </Link>
        </div>
      </div>
      <div>
        <div className="w-full flex justify-end space-x-1">
          <Input
              type="text"
              placeholder="Patient Name"
              value={patientName}
              onChange={handleInputChange}
              className="w-80"
            />
          <Button variant="outline" size="icon" onClick={loadDataByDate}>
            <Search className="w-5 h-5" />
          </Button>
        </div>
      </div>
      <div>
        <Table>
          <TableCaption>A list of appointments.</TableCaption>
          <TableHeader>
            <TableRow>
              <TableHead>Id</TableHead>
              <TableHead>Date</TableHead>
              <TableHead>Time</TableHead>
              <TableHead>Patient</TableHead>
              <TableHead>Patient Mobile</TableHead>
              <TableHead>Appointment Status</TableHead>
              <TableHead>Registration Fee</TableHead>
              <TableHead>Actions</TableHead>
            </TableRow>
          </TableHeader>
          <TableBody>
            {data.map((item, index) => (
              <TableRow key={index}>
                <TableCell>{item.appointmentId}</TableCell>
                <TableCell>{item.appointmentDate}</TableCell>
                <TableCell>{item.appointmentTime}</TableCell>
                <TableCell>
                  {item.patient.firstName + " " + item.patient.lastName}
                </TableCell>
                <TableCell>{item.patient.mobile}</TableCell>
                <TableCell>{item.status}</TableCell>
                <TableCell>{item.regFeeStatus}</TableCell>
                <TableCell className="items-center">
                  <Button
                    onClick={() => handleEdit(item.appointmentId)}
                    variant="secondary"
                    size="icon"
                  >
                    <Edit className="w-4 h-4" />
                  </Button>

                  <AlertDialog>
                    <AlertDialogTrigger>
                      {item.status == "COMPLETE" ? (
                        <ToggleRight className="w-4 h-4" />
                      ) : (
                        <ToggleLeft className="w-4 h-4" />
                      )}
                    </AlertDialogTrigger>
                    <AlertDialogContent>
                      <AlertDialogHeader>
                        <AlertDialogTitle>
                          {item.status == "PENDING"
                            ? "Select Treatments"
                            : "Change Status"}
                        </AlertDialogTitle>
                        <div>
                          {item.status == "PENDING" && (
                            <div className="space-y-2">
                              {treatmentTypes.map((item, index) => (
                                <div key={index} className="space-x-2">
                                  <input
                                    className="accent-black"
                                    type="checkbox"
                                    id={item.treatmentTypeName}
                                    checked={checkedIds.includes(
                                      item.id
                                    )}
                                    onChange={() =>
                                      handleCheckboxChange(item.id)
                                    }
                                  />
                                  <label htmlFor={item.treatmentTypeName}>
                                    {item.treatmentTypeName}
                                  </label>
                                </div>
                              ))}
                            </div>
                          )}
                        </div>
                      </AlertDialogHeader>
                      <AlertDialogFooter>
                        <AlertDialogCancel>Cancel</AlertDialogCancel>
                        {item.status == "PENDING" ? (
                          <AlertDialogAction
                            onClick={() =>
                              handleAppointmentStatus(item.appointmentId)
                            }
                          >
                            Complete
                          </AlertDialogAction>
                        ) : (
                          <AlertDialogAction
                            onClick={() =>
                              handleAppointmentStatus(item.appointmentId)
                            }
                          >
                            Pending
                          </AlertDialogAction>
                        )}
                      </AlertDialogFooter>
                    </AlertDialogContent>
                  </AlertDialog>
                  <Button
                    onClick={() => handleRegFeeStatus(item.appointmentId)}
                    variant="secondary"
                    size="icon"
                  >
                    {item.regFeeStatus == "PAID" ? (
                      <ToggleRight className="w-4 h-4" />
                    ) : (
                      <ToggleLeft className="w-4 h-4" />
                    )}
                  </Button>
                  <Button
                    onClick={() => handleDelete(item.appointmentId)}
                    variant="secondary"
                    size="icon"
                  >
                    <Trash className="w-4 h-4" />
                  </Button>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </div>
    </div>
  );
};

export default AppointmentsByName;