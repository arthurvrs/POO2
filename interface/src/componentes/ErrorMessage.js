import classes from "./errorMessage.module.css";

function ErrorMessage(props) {
  return <div className={classes.div}>{props.children}</div>;
}

export default ErrorMessage;
