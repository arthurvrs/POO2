import React from "react";
import { useLocation } from "react-router-dom";

function LivroDetalhes() {
  const location = useLocation;
  const { titulo } = location.state;
  return (
    <div>
      Hello There
    </div>
  );
}

export default LivroDetalhes;
