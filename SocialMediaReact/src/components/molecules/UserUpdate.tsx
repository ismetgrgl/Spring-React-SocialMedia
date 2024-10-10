import React, { useState } from 'react'
import { SocialDispatch, useAppSelector } from '../../store'
import { useDispatch } from 'react-redux'
import { fetchEditUser } from '../../store/feature/userSlice'

function UserUpdate() {
    const [name, setname] = useState('')
    const [phone, setphone] = useState('')
    const [about, setabout] = useState('')
    const [adress, setadress] = useState('')
    const token = useAppSelector(state => state.auth.token);
    const dispatch = useDispatch<SocialDispatch>();

    const editUser = ()=>{
		dispatch(fetchEditUser({
			token: token,
            name: name,
            phone: phone,
            about: about,
            address: adress
		}))
	}
  return (
    <div className="container mt-4">
    <div className="row">
        <div className="col-md-6">
            <div className="mb-3">
                <label htmlFor="name" className="form-label">Name</label>
                <input id="name" onChange={(e)=>setname(e.target.value)} type="text" className="form-control" />
            </div>
            <div className="mb-3">
                <label htmlFor="about" className="form-label">About</label>
                <textarea id="about" onChange={(e)=>setabout(e.target.value)}  className="form-control" />
            </div>
        </div>
        <div className="col-md-6">
            
            <div className="mb-3">
                <label htmlFor="phone" className="form-label">Phone</label>
                <input id="phone" onChange={(e)=>setphone(e.target.value)} type="text" className="form-control" />
            </div>
            <div className="mb-3">
                <label htmlFor="address" className="form-label">Address</label>
                <textarea id="address" onChange={(e)=>setadress(e.target.value)}  className="form-control" />
            </div>
        </div>
    </div>
    <div className="row">
        <div className="col">
            <button onClick={editUser} className="btn btn-primary">Update</button>
        </div>
    </div>
</div>
  )
}

export default UserUpdate