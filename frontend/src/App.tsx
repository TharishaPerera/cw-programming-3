import { BrowserRouter, Route, Routes } from "react-router-dom";
import AuthLayout from "./layouts/AuthLayout";
import LoginPage from "./routes/auth/Login";
import MainLayout from "./layouts/MainLayout";
import { Toaster } from "sonner";
import LandingPage from "./routes/LandingPage";
import MenuPage from "./routes/home/menu";
import Schedules from "./routes/home/schedules";
import Appointments from "./routes/home/appointment";
import Patients from "./routes/home/patients";
import Dentists from "./routes/home/dentist";
import Invoices from "./routes/home/invoices";
import ViewInvoice from "./routes/home/invoices/view";
import Payment from "./routes/home/payments";
import CreateAppointment from "./routes/home/appointment/create";
import UpdateAppointment from "./routes/home/appointment/update";
import AppointmentsByDate from "./routes/home/appointment/byDate";
import AppointmentsByName from "./routes/home/appointment/byName";
import AppointmentsById from "./routes/home/appointment/byId";

function App() {
  return (
    <BrowserRouter>
      <div className="bg-neutral-100 min-h-screen">
        <Routes>
          <Route path="/login" element={<AuthLayout />}>
            <Route index element={<LoginPage />} />
          </Route>
          <Route path="/" element={<MainLayout />}>
            <Route index element={<LandingPage />} />
            <Route path="home" element={<MenuPage />} />
            {/* Appointments */}
            <Route path="/appointments">
              <Route index element={<Appointments />} />
              <Route path="create" element={<CreateAppointment />} />
              <Route path="update/:id" element={<UpdateAppointment />} />
              <Route path="by-date" element={<AppointmentsByDate />} />
              <Route path="by-appointment-id" element={<AppointmentsById />} />
              <Route path="by-name" element={<AppointmentsByName />} />
            </Route>
            {/* Schedules */}
            <Route path="/schedules">
              <Route index element={<Schedules />} />
            </Route>
            {/* Dentists */}
            <Route path="/dentists">
              <Route index element={<Dentists />} />
              {/* <Route path="create" element={<CreateAppointment />} />
              <Route path="update/:id" element={<UpdateAppointment />} /> */}
            </Route>
            {/* Patients */}
            <Route path="/patients">
              <Route index element={<Patients />} />
            </Route>
            {/* Invoices */}
            <Route path="/invoices">
              <Route index element={<Invoices />} />
              <Route path="view/:id" element={<ViewInvoice />} />
            </Route>
            {/* Payments */}
            <Route path="/payments">
              <Route index element={<Payment />} />
              <Route path="view/:id" element={<ViewInvoice />} />
            </Route>
          </Route>
        </Routes>
        <Toaster />
      </div>
    </BrowserRouter>
  );
}

export default App;
