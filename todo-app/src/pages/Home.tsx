import { useEffect, useState } from "react"
import { addTodo, getAllTodos } from "../Auth"
import "../styles/home.css"

export default function Home() {
  const [todos, setTodos] = useState<any[]>([]);
  const [newTodo, setNewTodo] = useState<string>("");

  useEffect(() => {
    getAllTodos().then((data) => {
      setTodos(data);
    });
  }, []);

  function handleChange(id: any): void {
    console.log(id);
  }
  function handleSunmit(id: any): void {
    console.log(id);
  }
  function addTodoHandler(): void {
    addTodo(newTodo).then((data) => {
      if (data) {
        getAllTodos().then((data) => {
          setNewTodo("");
          setTodos(data);
        });
      } else {
        alert("Ekleme işlemi başarısız oldu.");
      }
    });
  }

  return (
    <div className="base">
      <h1 className="header">Todos</h1>
      <div className="add-todo">
        <input type="text" placeholder="Yapılacaklar" value={newTodo} onChange={(e)=>{setNewTodo(e.target.value)}} required />
        <button onClick={()=>{addTodoHandler()}} >Add</button>
      </div>
      { todos && todos.map((todo) => {
        return (
          <div className="todo-item" key={todo.id}>
            <input type="checkbox" checked={todo.completed} onChange={() => handleChange(todo.id)}/>
            <p className="todo-text">{todo.description}</p>
            <input className="todo-actions" type="button" value="Sil" onClick={()=>handleSunmit(todo.id)} />
          </div>
        )
      })}
    </div>
  )
}
