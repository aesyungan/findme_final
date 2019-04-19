using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using CapaEntidades;
using System.Data.SqlClient;
using System.Data;

namespace CapaAccesoAtos
{
  public  class AsignacionAD
    {
        #region "PATRON SINGLETON"
        private static AsignacionAD asignacionAD = null;
        public AsignacionAD()
        {

        }
        public static AsignacionAD getInstance()
        {
            if (asignacionAD == null)
            {
                asignacionAD = new AsignacionAD();
            }
            return asignacionAD;
        }
        #endregion
        public List<Asignacion> Listar()
        {
            List<Asignacion> listReturn = new List<Asignacion>();

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;

            try
            {
                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_mostrar_asignacion, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                //abrimos Coneccion
                coneccion.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())//si existe datos
                {
                    Dispositivo item_dispositivo = new Dispositivo();
                    Usuario usuarioreturn = new Usuario();
                    Asignacion item_asignacion = new Asignacion();
                    //dispositivo
                    item_dispositivo.id_dispositivo = Convert.ToInt32(reader["id_dispositivo"].ToString());
                    item_dispositivo.descripcion = (reader["descripcion"].ToString());
                    item_dispositivo.imei = reader["imei"].ToString();
                    item_dispositivo.fecha_update_registro = Convert.ToDateTime(reader["fecha_update_registro"].ToString());
                    item_dispositivo.latitud = Convert.ToDouble(reader["latitud"].ToString());
                    item_dispositivo.longitud = Convert.ToDouble(reader["longitud"].ToString());
                    item_dispositivo.altura = Convert.ToDouble(reader["altura"].ToString());
                    item_dispositivo.fechaupdate = Convert.ToDateTime(reader["fechaupdate"].ToString());
                    //usuario
                 
                    usuarioreturn.id_usuario = Convert.ToInt32(reader["id_usuario"].ToString());
                    usuarioreturn.rol.id_rol = Convert.ToInt32(reader["id_rol"].ToString());
                    usuarioreturn.nombres = reader["nombres"].ToString();
                    usuarioreturn.apellidos = reader["apellidos"].ToString();
                    usuarioreturn.cedula = reader["cedula"].ToString();
                    usuarioreturn.foto = reader["foto"].ToString();
                    usuarioreturn.usuario = reader["usuario"].ToString();
                    usuarioreturn.contrasena = reader["contrasena"].ToString();
                    usuarioreturn.estado = Convert.ToInt32(reader["estado"].ToString());
                    usuarioreturn.edad = Convert.ToInt32(reader["edad"].ToString());
                    usuarioreturn.direccion = reader["direccion"].ToString();
                    //asignacion
                    item_asignacion.id_asignacion = Convert.ToInt32(reader["id_asignacion"].ToString()); 
                    item_asignacion.usuario_ = usuarioreturn;
                    item_asignacion.dispositivo = item_dispositivo;
                    item_asignacion.propietario = Convert.ToInt32(reader["propietario"].ToString());

                    listReturn.Add(item_asignacion);

                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return listReturn;
        }


        //logeo para que muestre todos los datos

        public List<Asignacion> Logueo(Usuario usuario)
        {
            List<Asignacion> listReturn = new List<Asignacion>();

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            SqlDataReader reader = null;

            try
            {
                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_veriicarUsuario, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.AddWithValue("@user", usuario.usuario);
                cmd.Parameters.AddWithValue("@pass", usuario.contrasena);

                //abrimos Coneccion
                coneccion.Open();
                reader = cmd.ExecuteReader();
                while (reader.Read())//si existe datos
                {
                    Dispositivo item_dispositivo = new Dispositivo();
                    Usuario usuarioreturn = new Usuario();
                    Asignacion item_asignacion = new Asignacion();
                    //dispositivo
                    item_dispositivo.id_dispositivo = Convert.ToInt32(reader["id_dispositivo"].ToString());
                    item_dispositivo.descripcion = (reader["descripcion"].ToString());
                    item_dispositivo.imei = reader["imei"].ToString();
                    item_dispositivo.fecha_update_registro = Convert.ToDateTime(reader["fecha_update_registro"].ToString());
                    item_dispositivo.latitud = Convert.ToDouble(reader["latitud"].ToString());
                    item_dispositivo.longitud = Convert.ToDouble(reader["longitud"].ToString());
                    item_dispositivo.altura = Convert.ToDouble(reader["altura"].ToString());
                    item_dispositivo.fechaupdate = Convert.ToDateTime(reader["fechaupdate"].ToString());
                    //usuario

                    usuarioreturn.id_usuario = Convert.ToInt32(reader["id_usuario"].ToString());
                    usuarioreturn.rol.id_rol = Convert.ToInt32(reader["id_rol"].ToString());
                    usuarioreturn.nombres = reader["nombres"].ToString();
                    usuarioreturn.apellidos = reader["apellidos"].ToString();
                    usuarioreturn.cedula = reader["cedula"].ToString();
                    usuarioreturn.foto = reader["foto"].ToString();
                    usuarioreturn.usuario = reader["usuario"].ToString();
                    usuarioreturn.contrasena = reader["contrasena"].ToString();
                    usuarioreturn.estado = Convert.ToInt32(reader["estado"].ToString());
                    usuarioreturn.edad = Convert.ToInt32(reader["edad"].ToString());
                    usuarioreturn.direccion = reader["direccion"].ToString();
                    //asignacion
                    item_asignacion.id_asignacion = Convert.ToInt32(reader["id_asignacion"].ToString());
                    item_asignacion.usuario_ = usuarioreturn;
                    item_asignacion.dispositivo = item_dispositivo;
                    item_asignacion.propietario = Convert.ToInt32(reader["propietario"].ToString());

                    listReturn.Add(item_asignacion);

                }
            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return listReturn;
        }


        public int Ingreso(Asignacion item)
        {

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;

            int correcto = 0;
            try
            {

                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_insertar_asignacion, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;

                cmd.Parameters.AddWithValue("@id_usuario", item.usuario_.id_usuario);
                cmd.Parameters.AddWithValue("@id_dispositivo", item.dispositivo.id_dispositivo);
                cmd.Parameters.AddWithValue("@propietario", item.propietario);
                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return correcto;
        }

        public int Update(Asignacion item)
        {

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;

            int correcto = 0;
            try
            {

                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_update_asignacion, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@id_asignacion", item.id_asignacion);
                cmd.Parameters.AddWithValue("@id_usuario", item.usuario_.id_usuario);
                cmd.Parameters.AddWithValue("@id_dispositivo", item.dispositivo.id_dispositivo);
                cmd.Parameters.AddWithValue("@propietario", item.propietario);
                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return correcto;
        }
        public int Remove(Asignacion item)
        {

            SqlConnection coneccion = null;
            SqlCommand cmd = null;
            // SqlDataReader reader = null;

            int correcto = 0;
            try
            {

                //optenemos coneccion
                coneccion = Conexion.getInstance().ConeccionDB();
                //nombre Procedimiento
                cmd = new SqlCommand(SettingsApp.Default.p_remove_asignacion, coneccion);
                //especificamos el tipo de procediemto ene ste caso es un procedimento
                cmd.CommandType = CommandType.StoredProcedure;
                cmd.Parameters.AddWithValue("@id_asignacion", item.id_asignacion);
                //abrimos Coneccion
                coneccion.Open();
                //sejecuta y retorna las filas afectadas
                correcto = cmd.ExecuteNonQuery();

            }
            catch (Exception ex)
            {

                throw ex;
            }
            finally
            {
                coneccion.Close();
            }
            return correcto;
        }

    }
}
