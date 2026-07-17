
use DB_ProyectoFM
go


go
if(OBJECT_ID('USP_Marca_Guardar') is not null)
begin
	drop proc USP_Marca_Guardar
end
GO
CREATE PROCEDURE USP_Marca_Guardar
@Marca_Detalle varchar(150)
as
begin try
	
	if exists(select Marca_Id from Marca where Marca_Detalle=@Marca_Detalle and Activo=1)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	insert  Marca(Marca_Detalle,Activo)values (@Marca_Detalle,1)

	declare @id int = @@identity
	select 1[Respuesta],'Registro guardado correctamente'[Mensaje],@id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO


if(OBJECT_ID('USP_Marca_Actualizar') is not null)
begin
	drop proc USP_Marca_Actualizar
end
GO
CREATE PROCEDURE USP_Marca_Actualizar
@Marca_Id int,
@Marca_Detalle varchar(150)
as
begin try
	
	if exists(select Marca_Id from Marca where Marca_Detalle=@Marca_Detalle and Activo=1 and Marca_Id!=@Marca_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	update Marca set Marca_Detalle=@Marca_Detalle where Marca_Id=@Marca_Id

	select 1[Respuesta],'Registro actualizado correctamente'[Mensaje],@Marca_Id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO

if(OBJECT_ID('USP_Marca_Eliminar') is not null)
begin
	drop proc USP_Marca_Eliminar
end
GO
CREATE PROCEDURE USP_Marca_Eliminar
@Marca_Id int
as
begin try
	if not exists(select Marca_Id from Marca where Activo=1 and Marca_Id=@Marca_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra eliminado. Actualice la lista.'[Mensaje],0 [Id]
		return
	end
	update Marca set Activo=0 where Marca_Id=@Marca_Id
	
	select 1[Respuesta],'Registro eliminado correctamente'[Mensaje]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO
GO




if(OBJECT_ID('USP_Marca_Listar') is not null)
begin
	drop proc USP_Marca_Listar
end
GO
CREATE PROCEDURE USP_Marca_Listar
as
begin
	
	select * from Marca where Activo=1
	
end 
GO


---
if(OBJECT_ID('USP_Categoria_Guardar') is not null)
begin
	drop proc USP_Categoria_Guardar
end
GO
CREATE PROCEDURE USP_Categoria_Guardar
@Categoria_Detalle varchar(150)
as
begin try
	
	if exists(select Categoria_Id from Categoria where Categoria_Detalle=@Categoria_Detalle and Activo=1)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	insert  Categoria(Categoria_Detalle,Activo)values (@Categoria_Detalle,1)

	declare @id int = @@identity
	select 1[Respuesta],'Registro guardado correctamente'[Mensaje],@id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO

if(OBJECT_ID('USP_Categoria_Actualizar') is not null)
begin
	drop proc USP_Categoria_Actualizar
end
GO
CREATE PROCEDURE USP_Categoria_Actualizar
@Categoria_Id int,
@Categoria_Detalle varchar(150)
as
begin try
	
	if exists(select Categoria_Id from Categoria where Categoria_Detalle=@Categoria_Detalle and Activo=1 and Categoria_Id!=@Categoria_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	update Categoria set Categoria_Detalle=@Categoria_Detalle where Categoria_Id=@Categoria_Id

	select 1[Respuesta],'Registro actualizado correctamente'[Mensaje],@Categoria_Id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO


if(OBJECT_ID('USP_Categoria_Eliminar') is not null)
begin
	drop proc USP_Categoria_Eliminar
end
GO
CREATE PROCEDURE USP_Categoria_Eliminar
@Categoria_Id int
as
begin try
	if not exists(select Categoria_Id from Categoria where Activo=1 and Categoria_Id=@Categoria_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra eliminado. Actualice la lista.'[Mensaje],0 [Id]
		return
	end
	update Categoria set Activo=0 where Categoria_Id=@Categoria_Id
	
	select 1[Respuesta],'Registro eliminado correctamente'[Mensaje]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO
GO


if(OBJECT_ID('USP_Categoria_Listar') is not null)
begin
	drop proc USP_Categoria_Listar
end
GO
CREATE PROCEDURE USP_Categoria_Listar
as
begin
	
	select * from Categoria where Activo=1
	
end 
GO


---


---
if(OBJECT_ID('USP_Laboratorio_Guardar') is not null)
begin
	drop proc USP_Laboratorio_Guardar
end
GO
CREATE PROCEDURE USP_Laboratorio_Guardar
@Laboratorio_Detalle varchar(150)
as
begin try
	
	if exists(select Laboratorio_Id from Laboratorio where Laboratorio_Detalle=@Laboratorio_Detalle and Activo=1)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	insert  Laboratorio(Laboratorio_Detalle,Activo)values (@Laboratorio_Detalle,1)

	declare @id int = @@identity
	select 1[Respuesta],'Registro guardado correctamente'[Mensaje],@id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO

if(OBJECT_ID('USP_Laboratorio_Actualizar') is not null)
begin
	drop proc USP_Laboratorio_Actualizar
end
GO
CREATE PROCEDURE USP_Laboratorio_Actualizar
@Laboratorio_Id int,
@Laboratorio_Detalle varchar(150)
as
begin try
	
	if exists(select Laboratorio_Id from Laboratorio where Laboratorio_Detalle=@Laboratorio_Detalle and Activo=1 and Laboratorio_Id!=@Laboratorio_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	update Laboratorio set Laboratorio_Detalle=@Laboratorio_Detalle where Laboratorio_Id=@Laboratorio_Id

	select 1[Respuesta],'Registro actualizado correctamente'[Mensaje],@Laboratorio_Id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO


if(OBJECT_ID('USP_Laboratorio_Eliminar') is not null)
begin
	drop proc USP_Laboratorio_Eliminar
end
GO
CREATE PROCEDURE USP_Laboratorio_Eliminar
@Laboratorio_Id int
as
begin try
	if not exists(select Laboratorio_Id from Laboratorio where Activo=1 and Laboratorio_Id=@Laboratorio_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra eliminado. Actualice la lista.'[Mensaje],0 [Id]
		return
	end
	update Laboratorio set Activo=0 where Laboratorio_Id=@Laboratorio_Id
	
	select 1[Respuesta],'Registro eliminado correctamente'[Mensaje]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO
GO


if(OBJECT_ID('USP_Laboratorio_Listar') is not null)
begin
	drop proc USP_Laboratorio_Listar
end
GO
CREATE PROCEDURE USP_Laboratorio_Listar
as
begin
	
	select * from Laboratorio where Activo=1
	
end 
GO





if(OBJECT_ID('USP_TipoDocumento_Listar') is not null)
begin
	drop proc USP_TipoDocumento_Listar
end
GO
CREATE PROCEDURE USP_TipoDocumento_Listar
as
begin
	
	select * from TipoDocumento 
	
end 
GO


if(OBJECT_ID('USP_TipoDocumentoVenta_Listar') is not null)
begin
	drop proc USP_TipoDocumentoVenta_Listar
end
GO
CREATE PROCEDURE USP_TipoDocumentoVenta_Listar
as
begin
	
	select * from TipoDocumentoVenta 
	
end 
GO

--PRODUCTO
go
if(OBJECT_ID('USP_Producto_Guardar') is not null)
begin
	drop proc USP_Producto_Guardar
end
GO
CREATE PROCEDURE USP_Producto_Guardar
--@Producto_Id INT,
@Codigo VARCHAR(20),
@Nombre VARCHAR(250),
@Precio DECIMAL(10,2),
@Stock INT,
@FechaVencimiento DATE,
@Laboratorio_Id int,
@Marca_Id int,
@Categoria_Id int
as
begin try
	
	if exists(select Producto_Id from Producto where Nombre=@Nombre and Activo=1)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	if(@Codigo='' or @Codigo=null)
	BEGIN
		DECLARE @COD_N INT 
		SELECT @COD_N=ISNULL(CONVERT(INTEGER, MAX(REPLACE(codigo,'p',''))),0)+1 FROM PRODUCTO
		set @Codigo = 'P'+ right(('0000000'+convert(varchar,@COD_N)),7)

	END

	insert  Producto(Codigo,Nombre,Precio,Stock,FechaVencimiento,Laboratorio_Id,Marca_Id,Categoria_Id,Activo)values 
	(@Codigo,@Nombre,@Precio,@Stock,@FechaVencimiento,@Laboratorio_Id,@Marca_Id,@Categoria_Id,1)

	declare @id int = @@identity
	select 1[Respuesta],'Registro guardado correctamente'[Mensaje],@id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO


if(OBJECT_ID('USP_Producto_Actualizar') is not null)
begin
	drop proc USP_Producto_Actualizar
end
GO
CREATE PROCEDURE USP_Producto_Actualizar
@Producto_Id INT,
@Codigo VARCHAR(20),
@Nombre VARCHAR(250),
@Precio DECIMAL(10,2),
@Stock INT,
@FechaVencimiento DATE,
@Laboratorio_Id int,
@Marca_Id int,
@Categoria_Id int
as
begin try
	
	if exists(select Producto_Id from Producto where Nombre=@Nombre and Activo=1 and Producto_Id!=@Producto_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra registrado.'[Mensaje],0 [Id]
		return
	end

	update Producto set Codigo=@Codigo,Nombre=@Nombre,Precio=@Precio,
	Stock=@Stock,FechaVencimiento=@FechaVencimiento,Laboratorio_Id=@Laboratorio_Id,
	Marca_Id=@Marca_Id,Categoria_Id=@Categoria_Id
	where Producto_Id=@Producto_Id

	select 1[Respuesta],'Registro actualizado correctamente'[Mensaje],@Producto_Id [Id]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO

if(OBJECT_ID('USP_Producto_Eliminar') is not null)
begin
	drop proc USP_Producto_Eliminar
end
GO
CREATE PROCEDURE USP_Producto_Eliminar
@Producto_Id int
as
begin try
	if not exists(select Producto_Id from Producto where Activo=1 and Producto_Id=@Producto_Id)
	begin
		select 0[Respuesta],'El registro ya se encuentra eliminado. Actualice la lista.'[Mensaje],0 [Id]
		return
	end
	update Producto set Activo=0 where Producto_Id=@Producto_Id
	
	select 1[Respuesta],'Registro eliminado correctamente'[Mensaje]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO
GO


if(OBJECT_ID('USP_Producto_Listar') is not null)
begin
	drop proc USP_Producto_Listar
end
GO
CREATE PROCEDURE USP_Producto_Listar
as
begin
	
	select
	p.*,
	l.Laboratorio_Detalle,m.Marca_Detalle,c.Categoria_Detalle
	from Producto p 
	inner join Laboratorio l on p.Laboratorio_Id=l.Laboratorio_Id
	inner join Marca m on p.Marca_Id=m.Marca_Id
	inner join Categoria c on p.Categoria_Id = c.Categoria_Id
	where p.Activo=1
	
end 
GO

if(OBJECT_ID('USP_Producto_ActualizarStock') is not null)
begin
	drop proc USP_Producto_ActualizarStock
end
GO
CREATE PROCEDURE USP_Producto_ActualizarStock
@Producto_Id int,
@Stock int
as
begin try
	
	update Producto set Stock+=@Stock where Producto_Id=@Producto_Id
	
	select 1[Respuesta],'Stock actualizado'[Mensaje]
end try
begin catch
	select 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]
end catch
GO
GO
---======================== SP CLIENTE ========================---
IF (OBJECT_ID('USP_Cliente_Guardar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_Cliente_Guardar
END
GO
CREATE PROCEDURE USP_Cliente_Guardar
(
    @TipoDocumento_Id INT,
    @NumeroDocumento VARCHAR(20),
    @Nombre VARCHAR(100),
    @Telefono VARCHAR(20)
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

        IF EXISTS (SELECT Cliente_Id FROM Cliente WHERE Activo=1 AND NumeroDocumento = @NumeroDocumento)
        BEGIN
            SELECT 0[Respuesta], 'El número de documento ya se encuentra registrado.'[Mensaje],0 [Id]
            RETURN
        END

        INSERT INTO Cliente(TipoDocumento_Id,NumeroDocumento,Nombre,Telefono,PuntosAcumulados,Activo)
        VALUES(@TipoDocumento_Id,@NumeroDocumento,@Nombre,@Telefono,0,1);

		DECLARE @id int = @@identity
        SELECT 1[Respuesta],'Cliente registrado correctamente.'[Mensaje],@id [Id]

    END TRY
    BEGIN CATCH

		SELECT 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO


IF (OBJECT_ID('USP_Cliente_Actualizar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_Cliente_Actualizar
END
GO
CREATE PROCEDURE USP_Cliente_Actualizar
(
    @Cliente_Id INT,
    @TipoDocumento_Id INT,
    @NumeroDocumento VARCHAR(20),
    @Nombre VARCHAR(100),
    @Telefono VARCHAR(20)
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

	    IF NOT EXISTS(
			SELECT Cliente_Id FROM Cliente WHERE Activo=1 AND Cliente_Id = @Cliente_Id
        )
        BEGIN
            SELECT 0[Respuesta],'El cliente no existe.' [Mensaje],0 [Id]
            RETURN
        END
        -- Verificar que el número de documento no pertenezca a otro cliente
        IF EXISTS (
            SELECT 1 FROM Cliente WHERE NumeroDocumento = @NumeroDocumento AND Cliente_Id <> @Cliente_Id
        )
        BEGIN
            SELECT 0[Respuesta],'El número de documento ya está registrado.' [Mensaje],0 [Id]
            RETURN
        END

        UPDATE Cliente
        SET
            TipoDocumento_Id = @TipoDocumento_Id,
            NumeroDocumento = @NumeroDocumento,
            Nombre = @Nombre,
            Telefono = @Telefono
        WHERE Cliente_Id = @Cliente_Id

        SELECT 1[Respuesta], 'Cliente actualizado correctamente.' [Mensaje],@Cliente_Id [Id]

    END TRY
    BEGIN CATCH

        SELECT 0[Respuesta], ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO

IF (OBJECT_ID('USP_Cliente_Eliminar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_Cliente_Eliminar
END
GO
CREATE PROCEDURE USP_Cliente_Eliminar
(
    @id INT
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

		IF NOT EXISTS(SELECT Cliente_Id FROM Cliente WHERE Activo = 1 and Cliente_Id = @id)
		BEGIN
            SELECT 0[Respuesta], 'El registro ya se encuentra eliminado. Actualice la lista..' [Mensaje],0 [Id]
            RETURN
        END

        UPDATE Cliente SET Activo = 0 WHERE Cliente_Id = @id

        SELECT 1[Respuesta], 'Cliente eliminado correctamente.' [Mensaje]

    END TRY
    BEGIN CATCH

        SELECT 0[Respuesta], ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO

IF (OBJECT_ID('USP_Cliente_Listar') IS NOT NULL)
BEGIN
    DROP PROC USP_Cliente_Listar
END
GO
CREATE PROCEDURE USP_Cliente_Listar
AS
BEGIN
   SELECT * FROM Cliente c
	INNER JOIN TipoDocumento td
    ON c.TipoDocumento_Id = td.TipoDocumento_Id
	WHERE c.Activo = 1
END
GO



--SELECT * FROM VENTA
--SELECT TOP 1 ISNULL(MAX(Correlativo),0)+1 FROM Venta WHERE TipoDocumentoVenta_Id=4 AND Activo=1
---======================== SP VENTA ========================---
IF (OBJECT_ID('USP_Venta_Guardar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_Venta_Guardar
END
GO
CREATE PROCEDURE USP_Venta_Guardar
(
    --Venta_Id int ,
	@Serie varchar(4),
	--@Correlativo int,
	@FechaCompleta datetime,
	@TipoDocumentoVenta_Id int,
	@Total decimal(9,2),
	@Cliente_Id int,
	@Usuario_Id int
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY
		declare @Correlativo int=0

		SELECT TOP 1 @Correlativo=ISNULL(MAX(Correlativo),0)+1 FROM Venta WHERE TipoDocumentoVenta_Id=@TipoDocumentoVenta_Id AND Activo=1

		declare @Estado int=1
		declare @Activo int=1
        INSERT INTO Venta(Serie,Correlativo,FechaCompleta,TipoDocumentoVenta_Id,Total
		,Cliente_Id,Usuario_Id,Estado,Activo)
        VALUES(@Serie,@Correlativo,@FechaCompleta,@TipoDocumentoVenta_Id,@Total
		,@Cliente_Id,@Usuario_Id,@Estado,@Activo);

		DECLARE @id int = @@identity
        SELECT 1[Respuesta],'Venta registrada correctamente.'[Mensaje],@id [Id]

    END TRY
    BEGIN CATCH

		SELECT 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO


IF (OBJECT_ID('USP_Venta_Actualizar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_Venta_Actualizar
END
GO
CREATE PROCEDURE USP_Venta_Actualizar
(
    @Venta_Id int ,
	@Serie varchar(4),
	--@Correlativo int,
	@FechaCompleta datetime,
	@TipoDocumentoVenta_Id int,
	@Total decimal(9,2),
	@Cliente_Id int,
	@Usuario_Id int
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

	    update Venta set Total=@Total where Venta_Id=@Venta_Id

        SELECT 1[Respuesta], 'Venta actualizado correctamente.' [Mensaje],@Venta_Id [Id]

    END TRY
    BEGIN CATCH

        SELECT 0[Respuesta], ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO

IF (OBJECT_ID('USP_Venta_Eliminar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_Venta_Eliminar
END
GO
CREATE PROCEDURE USP_Venta_Eliminar
(
    @id INT
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

		IF NOT EXISTS(SELECT Venta_Id FROM Venta WHERE Activo = 1 and Venta_Id = @id)
		BEGIN
            SELECT 0[Respuesta], 'El registro ya se encuentra eliminado. Actualice la lista..' [Mensaje],0 [Id]
            RETURN
        END

        UPDATE Venta SET Activo = 0 WHERE Venta_Id = @id

        SELECT 1[Respuesta], 'Venta eliminada correctamente.' [Mensaje]

    END TRY
    BEGIN CATCH

        SELECT 0[Respuesta], ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO

IF (OBJECT_ID('USP_Venta_Anular') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_Venta_Anular
END
GO
CREATE PROCEDURE USP_Venta_Anular
(
    @id INT
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

		IF EXISTS(SELECT Venta_Id FROM Venta WHERE Estado = 2 and Venta_Id = @id)
		BEGIN
            SELECT 0[Respuesta], 'El registro ya se encuentra anulado. Actualice la lista..' [Mensaje],0 [Id]
            RETURN
        END

        UPDATE Venta SET Estado = 2 WHERE Venta_Id = @id

        SELECT 1[Respuesta], 'Venta anulada correctamente.' [Mensaje]

    END TRY
    BEGIN CATCH

        SELECT 0[Respuesta], ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO

IF (OBJECT_ID('USP_Venta_Listar') IS NOT NULL)
BEGIN
    DROP PROC USP_Venta_Listar
END
GO
CREATE PROCEDURE USP_Venta_Listar
AS
BEGIN
   SELECT v.* 
   ,tdv.DocumentoVenta,c.Nombre,c.NumeroDocumento,td.Abreviatura
   FROM Venta v 
   inner join TipoDocumentoVenta tdv on v.TipoDocumentoVenta_Id=tdv.TipoDocumentoVenta_Id
   inner join Cliente c on v.Cliente_Id=c.Cliente_Id
   inner join TipoDocumento td on c.TipoDocumento_Id=td.TipoDocumento_Id

	WHERE v.Activo = 1
END
GO


select *from TipoDocumentoVenta
select * from VentaDetalle


---======================== SP VentaDetalle ========================---
IF (OBJECT_ID('USP_VentaDetalle_Guardar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_VentaDetalle_Guardar
END
GO
CREATE PROCEDURE USP_VentaDetalle_Guardar
(
--@VentaDetalle_Id int, 
@Venta_Id int,
@Producto_Id int,
@Cantidad int,
@PrecioUnitario decimal(9,2),
@Subtotal decimal(9,2)
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY
		
		declare @Activo int=1
        INSERT INTO VentaDetalle(Venta_Id,Producto_Id,
		Cantidad,PrecioUnitario,Subtotal,Activo)values
		(@Venta_Id,@Producto_Id,@Cantidad,@PrecioUnitario,
		@Subtotal,@Activo)
		DECLARE @id int = @@identity
        SELECT 1[Respuesta],'Detalle registrado correctamente.'[Mensaje],@id [Id]

    END TRY
    BEGIN CATCH

		SELECT 0[Respuesta],ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO


IF (OBJECT_ID('USP_VentaDetalle_Actualizar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_VentaDetalle_Actualizar
END
GO
CREATE PROCEDURE USP_VentaDetalle_Actualizar
(
@VentaDetalle_Id int, 
@Venta_Id int,
@Producto_Id int,
@Cantidad int,
@PrecioUnitario decimal(9,2),
@Subtotal decimal(9,2)
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

	    UPDATE VentaDetalle set Cantidad=@Cantidad, Subtotal=@Subtotal
		where VentaDetalle_Id=@VentaDetalle_Id
		SELECT 1[Respuesta],'Detalle actualizado correctamente.'[Mensaje],0 [Id]
    END TRY
    BEGIN CATCH

        SELECT 0[Respuesta], ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO

IF (OBJECT_ID('USP_VentaDetalle_Eliminar') IS NOT NULL)
BEGIN
    DROP PROCEDURE USP_VentaDetalle_Eliminar
END
GO
CREATE PROCEDURE USP_VentaDetalle_Eliminar
(
    @id INT
)
AS
BEGIN
    SET NOCOUNT ON
    BEGIN TRY

		IF NOT EXISTS(SELECT VentaDetalle_Id FROM VentaDetalle WHERE Activo = 1 and VentaDetalle_Id = @id)
		BEGIN
            SELECT 0[Respuesta], 'El registro ya se encuentra eliminado. Actualice la lista..' [Mensaje],0 [Id]
            RETURN
        END

        UPDATE VentaDetalle SET Activo = 0 WHERE VentaDetalle_Id = @id

        SELECT 1[Respuesta], 'VentaDetalle eliminada correctamente.' [Mensaje]

    END TRY
    BEGIN CATCH

        SELECT 0[Respuesta], ERROR_MESSAGE() [Mensaje],0 [Id]

    END CATCH
END
GO

IF (OBJECT_ID('USP_VentaDetalle_Listar') IS NOT NULL)
BEGIN
    DROP PROC USP_VentaDetalle_Listar
END
GO
CREATE PROCEDURE USP_VentaDetalle_Listar
AS
BEGIN
   SELECT * FROM VentaDetalle v 
	WHERE v.Activo = 1
END
GO

IF (OBJECT_ID('USP_VentaDetalle_ListarxID') IS NOT NULL)
BEGIN
    DROP PROC USP_VentaDetalle_ListarxID
END
GO
CREATE PROCEDURE USP_VentaDetalle_ListarxID
@Venta_Id int
AS
BEGIN
   SELECT v.*,
   p.Nombre
   FROM VentaDetalle v 
   inner join Producto p on v.Producto_Id=p.Producto_Id

	WHERE v.Activo = 1 and v.Venta_Id=@Venta_Id
END
GO

USP_VentaDetalle_ListarxID 2