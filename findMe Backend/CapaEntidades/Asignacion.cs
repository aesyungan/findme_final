using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CapaEntidades
{
  public  class Asignacion
    {
         public int id_asignacion { get; set; }
        public Usuario usuario_ { get; set; }
        public Dispositivo dispositivo { get; set; }
        public int propietario { get; set; }

        public Asignacion()
        {
            usuario_ = new Usuario();
            dispositivo = new Dispositivo();
        }
    }
}
