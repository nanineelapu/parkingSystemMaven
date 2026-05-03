import React, { useState, useEffect } from 'react';
import './index.css';

// If running via Vite dev server, point to localhost:8080. If deployed in Tomcat WAR, use relative path.
const API_BASE = window.location.hostname === 'localhost' && window.location.port === '5173' 
  ? 'http://localhost:8080' 
  : '.';

function App() {
  const [slots, setSlots] = useState({ totalSlots: 0, freeSlots: 0, occupiedSlots: 0 });
  const [vehicleNumber, setVehicleNumber] = useState('');
  const [vehicleType, setVehicleType] = useState('CAR');
  const [parkResult, setParkResult] = useState(null);
  const [parkError, setParkError] = useState(null);

  const [ticketId, setTicketId] = useState('');
  const [exitResult, setExitResult] = useState(null);
  const [exitError, setExitError] = useState(null);

  const fetchSlots = async () => {
    try {
      const res = await fetch(`${API_BASE}/slots/available`);
      const data = await res.json();
      setSlots(data);
    } catch (err) {
      console.error("Failed to fetch slots", err);
    }
  };

  useEffect(() => {
    fetchSlots();
    const interval = setInterval(fetchSlots, 5000);
    return () => clearInterval(interval);
  }, []);

  const handlePark = async (e) => {
    e.preventDefault();
    setParkResult(null);
    setParkError(null);
    try {
      const res = await fetch(`${API_BASE}/park`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ vehicleNumber, vehicleType })
      });
      if (!res.ok) {
        const err = await res.json();
        throw new Error(err.message || 'Failed to park. Parking full?');
      }
      const data = await res.json();
      setParkResult(data);
      setVehicleNumber('');
      fetchSlots();
    } catch (err) {
      setParkError(err.message);
    }
  };

  const handleExit = async (e) => {
    e.preventDefault();
    setExitResult(null);
    setExitError(null);
    try {
      const res = await fetch(`${API_BASE}/exit`, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ ticketId: parseInt(ticketId) })
      });
      if (!res.ok) {
        throw new Error('Failed to process exit. Check Ticket ID.');
      }
      const data = await res.json();
      setExitResult(data);
      setTicketId('');
      fetchSlots();
    } catch (err) {
      setExitError(err.message);
    }
  };

  return (
    <div className="app-container">
      <header className="header">
        <h1>ParkFlow</h1>
        <p>Premium Automated Parking System</p>
      </header>

      <div className="dashboard-grid">
        <div className="stat-card" style={{ animationDelay: '0.1s' }}>
          <div className="stat-value">{slots.totalSlots}</div>
          <div className="stat-label">Total Slots</div>
        </div>
        <div className="stat-card" style={{ animationDelay: '0.2s' }}>
          <div className="stat-value" style={{ color: 'var(--success)' }}>{slots.freeSlots}</div>
          <div className="stat-label">Available</div>
        </div>
        <div className="stat-card" style={{ animationDelay: '0.3s' }}>
          <div className="stat-value" style={{ color: 'var(--danger)' }}>{slots.occupiedSlots}</div>
          <div className="stat-label">Occupied</div>
        </div>
      </div>

      <div className="action-sections">
        <div className="action-card" style={{ animationDelay: '0.4s' }}>
          <h2>Entry Gate (Park Vehicle)</h2>
          <form onSubmit={handlePark}>
            <div className="form-group">
              <label>Vehicle Number</label>
              <input
                type="text"
                className="form-control"
                placeholder="e.g. AP16AB1234"
                value={vehicleNumber}
                onChange={(e) => setVehicleNumber(e.target.value.toUpperCase())}
                required
              />
            </div>
            <div className="form-group">
              <label>Vehicle Type</label>
              <select
                className="form-control"
                value={vehicleType}
                onChange={(e) => setVehicleType(e.target.value)}
              >
                <option value="CAR">Car</option>
                <option value="BIKE">Bike</option>
              </select>
            </div>
            <button type="submit" className="btn btn-primary" style={{ marginTop: '1rem' }}>Allocate Nearest Slot</button>
          </form>

          {parkError && (
            <div className="result-box" style={{ borderColor: 'var(--danger)', color: 'var(--danger)', background: 'rgba(239, 68, 68, 0.1)' }}>
              {parkError}
            </div>
          )}
          {parkResult && (
            <div className="result-box">
              ✅ Success! <br />
              Ticket ID: <strong>{parkResult.ticketId}</strong> <br />
              Assigned Slot: <strong>{parkResult.slotNumber}</strong>
            </div>
          )}
        </div>

        <div className="action-card" style={{ animationDelay: '0.5s' }}>
          <h2>Exit Gate (Checkout)</h2>
          <form onSubmit={handleExit} style={{ display: 'flex', flexDirection: 'column', height: '100%' }}>
            <div className="form-group">
              <label>Ticket ID</label>
              <input
                type="number"
                className="form-control"
                placeholder="Enter your Ticket ID"
                value={ticketId}
                onChange={(e) => setTicketId(e.target.value)}
                required
              />
            </div>
            <div style={{ flexGrow: 1 }}></div>
            <button type="submit" className="btn btn-danger" style={{ marginTop: 'auto' }}>Process Exit & Pay Fee</button>
          </form>

          {exitError && (
            <div className="result-box" style={{ borderColor: 'var(--danger)', color: 'var(--danger)', background: 'rgba(239, 68, 68, 0.1)' }}>
              {exitError}
            </div>
          )}
          {exitResult && (
            <div className="result-box">
              ✅ Checkout Complete! <br />
              Duration: <strong>{exitResult.duration}</strong> <br />
              Fee Calculated: <strong>₹{exitResult.amount}</strong>
            </div>
          )}
        </div>
      </div>
    </div>
  );
}

export default App;
