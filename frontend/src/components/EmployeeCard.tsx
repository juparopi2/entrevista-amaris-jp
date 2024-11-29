import { Employee } from "../interfaces/EmployeeInterface";

export default function EmployeeCard({ employee }: { employee: Employee }) {
  return (
    <div className="card h-100 border border-primary">
      <div className="row g-0">
        <div className="col-md-12">
          <div className="card-body">
            <img
              src={employee.profileImage}
              className="img-fluid rounded-circle mb-3"
              alt={employee.employeeName}
            />
            <h5 className="card-title text-primary">{employee.employeeName}</h5>
            <p className="card-text">
              <strong>{employee.employeeAge} years old</strong>
            </p>
            <p className="card-text">
              ${employee.employeeSalary.toLocaleString("es-ES")} / mo
            </p>
            {employee.employeeAnualSalary && (
              <p className="card-text">
                ${employee.employeeAnualSalary.toLocaleString("es-ES")} / year
              </p>
            )}
            <p className="card-text opacity-50">
              <small className="text-body-secondary">ID: {employee.id}</small>
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}
