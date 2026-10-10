import { Router } from 'express';
import { pool } from '../db.js';

const router = Router();

// GET: Obtener todos los productos
router.get('/', async (req, res) => {
  try {
    const { rows } = await pool.query('SELECT * FROM productos ORDER BY id ASC');
    res.json(rows);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// GET: Obtener un producto por ID
router.get('/:id', async (req, res) => {
  const { id } = req.params;
  try {
    const { rows } = await pool.query('SELECT * FROM productos WHERE id = $1', [id]);
    if (rows.length === 0) {
      return res.status(404).json({ mensaje: 'Producto no encontrado' });
    }
    res.json(rows[0]);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// POST: Crear un nuevo producto
router.post('/', async (req, res) => {
  const { nombre, categoria, precio } = req.body;
  try {
    const { rows } = await pool.query(
      'INSERT INTO productos (nombre, categoria, precio) VALUES ($1, $2, $3) RETURNING *',
      [nombre, categoria, precio]
    );
    res.status(201).json(rows[0]);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// PUT: Actualizar un producto
router.put('/:id', async (req, res) => {
  const { id } = req.params;
  const { nombre, categoria, precio, disponible } = req.body;
  try {
    const { rows } = await pool.query(
      `UPDATE productos 
       SET nombre = $1, categoria = $2, precio = $3, disponible = $4 
       WHERE id = $5 RETURNING *`,
      [nombre, categoria, precio, disponible, id]
    );
    if (rows.length === 0) {
      return res.status(404).json({ mensaje: 'Producto no encontrado' });
    }
    res.json(rows[0]);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

// DELETE: Eliminar un producto
router.delete('/:id', async (req, res) => {
  const { id } = req.params;
  try {
    const { rowCount } = await pool.query('DELETE FROM productos WHERE id = $1', [id]);
    if (rowCount === 0) {
      return res.status(404).json({ mensaje: 'Producto no encontrado' });
    }
    res.json({ mensaje: 'Producto eliminado correctamente' });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
});

export default router;