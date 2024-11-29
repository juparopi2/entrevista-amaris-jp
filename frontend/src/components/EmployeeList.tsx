import EmployeeCard from "./EmployeeCard";
import { useState, useEffect } from "react";
import { Employee } from "../interfaces/EmployeeInterface";

function EmployeeList() {
  const [searchId, setSearchId] = useState("");
  const [employees, setEmployees] = useState<Employee[]>([]);
  const [isLoading, setIsLoading] = useState(false);
  const [error, setError] = useState("");

  const BASE_URL = "http://localhost:8080/api/employees";

  useEffect(() => {
    fetchEmployeeData();
  }, []);

  const fetchEmployeeData = async (id?: string) => {
    setIsLoading(true);
    setError("");
    const url = id ? `${BASE_URL}/${id}` : BASE_URL;
    fetch(url)
      .then((response) => {
        if (!response.ok) {
          if (response.status === 404) {
            throw new Error("Empleado no encontrado");
          }
          throw new Error("Error en la red");
        }
        return response.json();
      })
      .then((employees: Employee[]) => {
        const employeesWithoutImage = employees.filter(
          (emp) => emp.profileImage === ""
        ).length;
        fetch(`https://randomuser.me/api/?results=${employeesWithoutImage}`)
          .then((response) => response.json())
          .then((images) => {
            const newImages = images.results.map(
              (user: any) => user.picture.large
            );
            const updatedEmployees = employees.map((emp, index) => {
              if (emp.profileImage === "") {
                emp.profileImage = newImages.pop() || emp.profileImage;
              }
              return emp;
            });
            return Promise.all(
              updatedEmployees.map((emp) => {
                return fetch(`${BASE_URL}/${emp.id}/annual-salary`)
                  .then((response) => response.json())
                  .then((data) => {
                    emp.employeeAnualSalary = data;
                    return emp;
                  });
              })
            );
          })
          .then((fullEmployeeData: Employee[]) => {
            setEmployees(fullEmployeeData);
            setIsLoading(false);
          });
      })
      .catch((error) => {
        console.error("Error fetching employee data:", error);
        setError(error.message);
        setIsLoading(false);
      });
  };
  const handleSearch = async () => {
    try {
      if (searchId.trim() === "") {
        setIsLoading(false);
        fetchEmployeeData();
        return;
      }
      fetchEmployeeData(searchId);
    } catch (error) {
      if (error instanceof Error) {
        setError(error.message);
      } else {
        setError("An unknown error occurred");
      }
    } finally {
      setIsLoading(false);
    }
  };

  return (
    <article className="container w-100 mt-5">
      <div className="container-sm w-75 mb-4">
        <div className="mx-auto">
          <div className="input-group">
            <input
              type="number"
              className="form-control me-1"
              placeholder="Ingrese ID del empleado"
              value={searchId}
              onChange={(e) => setSearchId(e.target.value)}
              onKeyDown={(e) => {
                if (e.key === "Enter") {
                  handleSearch();
                }
              }}
              aria-label="ID del empleado"
            />
            <button
              type="button"
              className="btn btn-primary"
              onClick={handleSearch}
              disabled={isLoading}
            >
              {isLoading ? (
                <>
                  <span
                    className="spinner-border spinner-border-sm me-2"
                    role="status"
                    aria-hidden="true"
                  ></span>
                  Buscando
                </>
              ) : (
                "Buscar Empleado"
              )}
            </button>
          </div>
          {error && <div className="text-danger mt-2">{error}</div>}
        </div>
      </div>

      {/* Grid de empleados */}
      {employees.length > 0 && (
        <div className="container-lg">
          <div
            className={`row row-cols-1 row-cols-md-${
              employees.length < 3 ? employees.length : 3
            } g-4`}
          >
            {employees.map((employee) => (
              <div key={employee.id} className="col">
                <EmployeeCard employee={employee} />
              </div>
            ))}
          </div>
        </div>
      )}
    </article>
  );
}

export default EmployeeList;
