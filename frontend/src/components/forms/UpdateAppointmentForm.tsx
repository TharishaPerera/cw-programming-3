import React, { useEffect, useState } from "react";
import { z } from "zod";
import { toast } from "sonner";
import { zodResolver } from "@hookform/resolvers/zod";
import { SubmitHandler, useForm, useWatch } from "react-hook-form";
import { CalendarIcon } from "lucide-react";

import { Input } from "@/components/ui/input";
import { Button } from "@/components/ui/button";
import {
  Popover,
  PopoverContent,
  PopoverTrigger,
} from "@/components/ui/popover";
import { Calendar } from "@/components/ui/calendar";
import { cn, format } from "@/lib/utils";
import { Separator } from "@/components/ui/separator";
import { API_URL, appointmentTimes } from "@/config/config";
import {
  Form,
  FormControl,
  FormField,
  FormItem,
  FormLabel,
  FormMessage,
} from "../ui/form";
import {
  Select,
  SelectContent,
  SelectItem,
  SelectTrigger,
  SelectValue,
} from "../ui/select";
import Loader from "../loader";

const AppointmentSchema = z.object({
  date: z.date({ required_error: "Please select appointment date" }),
  time: z.string({ required_error: "Please select time" }),
  regFeeStatus: z.string().optional(),
  dentist: z.string({ required_error: "Please select the dentist" }),
  firstName: z.string().min(1, "Please enter first name"),
  lastName: z.string().min(1, "Please enter last name"),
  email: z.string().min(1, "Please enter email address").email(),
  mobile: z.string().min(10, "Telephone number must have 10 digits"),
  address: z.string().min(1, "Please enter address"),
  dob: z.date({ required_error: "Please select the date of birth" }),
});

interface Dentist {
  userId: number;
  firstName: String;
  lastName: String;
}

interface Appointment {
  appointmentId: number;
  appointmentDate: string;
  appointmentTime: string;
  patient: {
    userId: number;
    firstName: string;
    lastName: string;
    email: string;
    mobile: number;
    userType: string;
    address: string;
    dob: string;
  };
  dentist: {
    userId: number;
  };
  regFeeStatus: string;
}

const UpdateAppointmentForm = (id: number) => {
  // yesterday date for date picker
  var today = new Date();
  today.setHours(0, 0, 0, 0);
  var yesterday = new Date(today);
  yesterday.setDate(today.getDate() - 1);

  const [dentists, setDentists] = useState<Dentist[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState(null);
  const [data, setData] = useState<Appointment>();

  useEffect(() => {
    const fetchData = async () => {
      try {
        setIsLoading(true);
        // get dentists
        const dentistResponse = await fetch(API_URL + "/users/dentists");
        if (!dentistResponse.ok) {
          throw new Error("Network response was not ok");
        }
        const dentists = await dentistResponse.json();
        setDentists(dentists);

        // get appointment
        const appointmentResponse = await fetch(API_URL + "/appointments/" + id.id);
        if (!appointmentResponse.ok) {
          toast.error("Something went wrong");
        }

        const appointment = await appointmentResponse.json();
        setData(appointment);
      } catch (error) {
        setError(error);
      } finally {
        setIsLoading(false);
      }
    };

    fetchData();
  }, []);

  const form = useForm<z.infer<typeof AppointmentSchema>>({
    resolver: zodResolver(AppointmentSchema),
    defaultValues: {
      date: new Date(),
      time: data?.appointmentTime.slice(0, 5),
      regFeeStatus: "",
      dentist: data?.dentist.userId.toString(),
      firstName: data?.patient.firstName,
      lastName: data?.patient.lastName,
      email: data?.patient.email,
      mobile: data?.patient.mobile.toString(),
      address: data?.patient.address,
      dob: new Date(),
    },
  });

  async function onSubmit(values: z.infer<typeof AppointmentSchema>) {
    try {
      // first create patient
      const patientResponse = await fetch(API_URL + "/users/patients", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          firstName: values.firstName,
          lastName: values.lastName,
          email: values.email,
          mobile: values.mobile,
          address: values.address,
          dob: format(values.dob),
        }),
      });
      if (!patientResponse.ok) {
        toast.error("Something went wrong");
      }
      const patient = await patientResponse.json();

      // then get the dentist
      const dentistResponse = await fetch(
        API_URL + "/users/dentists/" + values.dentist
      );
      if (!dentistResponse.ok) {
        toast.error("Dentist fetching failed");
      }
      const dentist = await dentistResponse.json();

      // then create the appointment
      const appointmentResponse = await fetch(API_URL + "/appointments", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify({
          appointmentDate: format(values.date),
          appointmentTime: values.time,
          patient: patient,
          dentist: dentist,
          regFeeStatus: values.regFeeStatus,
        }),
      });
      if (!appointmentResponse.ok) {
        toast.error("Something went wrong");
      }
      toast.success("Appointment created successfully");
      const redirectTo = () => {
        window.location.href = "/appointments";
      };
      setTimeout(redirectTo, 1000);
    } catch (error) {
      console.log(error);
      toast.error("Error occurred when creating the appointment");
      // const redirectTo = () => {
      //   window.location.href = "/appointments/create";
      // };
      // setTimeout(redirectTo, 1000);
    }
  }

  if (isLoading) {
    return <Loader />;
  }
  if (error) {
    return toast.error("Something went wrong. Please try again.");
  }

  return (
    <>
      <Form {...form}>
        <form onSubmit={form.handleSubmit(onSubmit)} className="space-y-4">
          <div className="grid grid-cols-4 gap-6">
            <div className="w-full mt-[10px]">
              <FormField
                control={form.control}
                name="date"
                render={({ field }) => (
                  <FormItem className="flex flex-col">
                    <FormLabel>Appointment Date</FormLabel>
                    <Popover>
                      <PopoverTrigger asChild>
                        <FormControl>
                          <Button
                            variant={"outline"}
                            className={cn(
                              "w-full justify-start text-left font-normal",
                              !field.value && "text-muted-foreground"
                            )}
                          >
                            {field.value ? (
                              format(field.value)
                            ) : (
                              <span>Pick a date</span>
                            )}
                            <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                          </Button>
                        </FormControl>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0" align="center">
                        <Calendar
                          mode="single"
                          selected={field.value}
                          onSelect={field.onChange}
                          disabled={(date) =>
                            date <= yesterday || date < new Date("1900-01-01")
                          }
                          initialFocus
                        />
                      </PopoverContent>
                    </Popover>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            <div>
              <FormField
                control={form.control}
                name="time"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Appointment Time</FormLabel>
                    <Select
                      onValueChange={field.onChange}
                      defaultValue={field.value}
                    >
                      <FormControl>
                        <SelectTrigger className="w-full">
                          <SelectValue placeholder="Appointment Time" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        {appointmentTimes.map((time, index) => (
                          <SelectItem key={index} value={time}>
                            {time}
                          </SelectItem>
                        ))}
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            <div>
              <FormField
                control={form.control}
                name="dentist"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Dentist</FormLabel>
                    <Select
                      onValueChange={field.onChange}
                      defaultValue={field.value}
                    >
                      <FormControl>
                        <SelectTrigger className="w-full">
                          <SelectValue placeholder="Dentist" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        {dentists &&
                          dentists.map((item, index) => (
                            <SelectItem
                              key={index}
                              value={item.userId.toString()}
                            >
                              {item.firstName + " " + item.lastName}
                            </SelectItem>
                          ))}
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>

            <div>
              <FormField
                control={form.control}
                name="regFeeStatus"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Registration Fee Status</FormLabel>
                    <Select
                      onValueChange={field.onChange}
                      defaultValue={field.value}
                    >
                      <FormControl>
                        <SelectTrigger className="w-full">
                          <SelectValue placeholder="Registration Fee" />
                        </SelectTrigger>
                      </FormControl>
                      <SelectContent>
                        <SelectItem value="PENDING">Pending</SelectItem>
                        <SelectItem value="PAID">Paid</SelectItem>
                      </SelectContent>
                    </Select>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
          </div>

          <Separator />

          <div className="grid grid-cols-3 gap-6">
            <div>
              <FormField
                control={form.control}
                name="firstName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Patient First Name</FormLabel>
                    <FormControl>
                      <Input placeholder="First Name" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div>
              <FormField
                control={form.control}
                name="lastName"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Patient Last Name</FormLabel>
                    <FormControl>
                      <Input placeholder="Last Name" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div>
              <FormField
                control={form.control}
                name="email"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Patient Email Address</FormLabel>
                    <FormControl>
                      <Input placeholder="username@example.com" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div>
              <FormField
                control={form.control}
                name="mobile"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Patient Mobile</FormLabel>
                    <FormControl>
                      <Input placeholder="0712345678" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div>
              <FormField
                control={form.control}
                name="address"
                render={({ field }) => (
                  <FormItem>
                    <FormLabel>Patient Address</FormLabel>
                    <FormControl>
                      <Input placeholder="1/1, Colombo" {...field} />
                    </FormControl>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
            <div className="w-full mt-[10px]">
              <FormField
                control={form.control}
                name="dob"
                render={({ field }) => (
                  <FormItem className="flex flex-col">
                    <FormLabel>Patient Date Of Birth</FormLabel>
                    <Popover>
                      <PopoverTrigger asChild>
                        <FormControl>
                          <Button
                            variant={"outline"}
                            className={cn(
                              "w-full justify-start text-left font-normal",
                              !field.value && "text-muted-foreground"
                            )}
                          >
                            {field.value ? (
                              format(field.value)
                            ) : (
                              <span>Pick a date</span>
                            )}
                            <CalendarIcon className="ml-auto h-4 w-4 opacity-50" />
                          </Button>
                        </FormControl>
                      </PopoverTrigger>
                      <PopoverContent className="w-auto p-0" align="center">
                        <Calendar
                          mode="single"
                          selected={field.value}
                          onSelect={field.onChange}
                          disabled={(date) =>
                            date > new Date() || date < new Date("1900-01-01")
                          }
                          initialFocus
                        />
                      </PopoverContent>
                    </Popover>
                    <FormMessage />
                  </FormItem>
                )}
              />
            </div>
          </div>
          <Button className="w-full font-bold text-md" type="submit">
            Submit
          </Button>
        </form>
      </Form>
    </>
  );
};

export default UpdateAppointmentForm;
