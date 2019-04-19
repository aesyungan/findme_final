using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CapaEntidades
{
  public  class Rol
    {

        public int id_rol { get; set; }
        public string descripcion_rol { get; set; }
        public int estado { get; set; }
        public Rol()
        {

        }
        public Rol(int id_rol,string  descripcion, int estado)
        {
            this.id_rol = id_rol;
            this.descripcion_rol = descripcion;
            this.estado = estado;
        }


    }
}
