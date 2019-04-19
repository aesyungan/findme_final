using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CapaEntidades
{
  public  class Usuario
    {
        public int id_usuario { get; set; }
        public Rol rol { get; set; }
        public string nombres { get; set; }
        public string apellidos { get; set; }
        public string cedula { get; set; }
        public string foto { get; set; }
        public string usuario { get; set; }
        public string contrasena { get; set; }
        public int estado { get; set; }
        public int edad { get; set; }
        public string direccion { get; set; }
        public Usuario()
        {
            this.rol = new Rol();
        }

    }
}
