export default function Menu() {
  return (
    <nav
      className="navbar bg-primary navbar-expand fixed-top"
      data-bs-theme="dark"
    >
      <div className="container-fluid">
        <a className="navbar-brand" href="/">
          <img
            src="/logo.png"
            alt="Logo"
            width="110"
            height="90"
            className="d-inline-block align-text-top"
          />
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a
                className="nav-link active"
                aria-current="page"
                href="https://jpromero.dev"
                target="_blank"
                rel="noopener noreferrer"
              >
                Portafolio
              </a>
            </li>
            <li className="nav-item">
              <a
                className="nav-link active"
                aria-current="page"
                href="/read-me"
              >
                Read Me
              </a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
  );
}
