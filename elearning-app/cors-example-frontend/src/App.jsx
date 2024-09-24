import "./App.css";

function App() {
  const appUrl = "http://localhost:8080/api/v1/categories";
  function getData() {
    fetch(appUrl)
      .then((response) =>
        response
          .json()
          .then((data) => {
            console.log("Data: ", data);
          })
          .then((error) => {
            console.log("Error: ", error);
          })
      )
      .catch((error) => {
        console.log("Error: ", error);
      });
  }
  return (
    <>
      <h1>Cors Example </h1>
      <h1> Popular Categories </h1>
      <div className="card">
        <button onClick={getData}> Get Data </button>
      </div>
    </>
  );
}

export default App;
