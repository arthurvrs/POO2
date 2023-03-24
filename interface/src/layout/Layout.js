import BarraDeNavegacao from './BarraDeNavegacao';
import classes from './Layout.module.css'



function Layout(props) {
  return (
    <div>
      <BarraDeNavegacao/>
      <main className={classes.main}>{props.children}</main>
    </div>
  );
}

export default Layout;
