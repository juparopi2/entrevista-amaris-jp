import "./App.css";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Menu from "./components/Menu";
import EmployeeList from "./components/EmployeeList";
import ReadMe from "./components/ReadMe";

function App() {
  return (
    <Router>
      <main className="container-xl p-0">
        <Menu />
        <Routes>
          <Route path="/" element={<EmployeeList />} />
          <Route path="/read-me" element={<ReadMe />} />
        </Routes>
      </main>
    </Router>
  );
}

export default App;
