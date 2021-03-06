package br.com.tairoroberto.lovedogs.petshop.presenter

import android.util.Log
import br.com.tairoroberto.lovedogs.base.extension.showSnackBarError
import br.com.tairoroberto.lovedogs.petshop.contract.PetshopContract
import br.com.tairoroberto.lovedogs.petshop.model.PetShop
import br.com.tairoroberto.lovedogs.petshop.model.PetshopModel
import br.com.tairoroberto.lovedogs.petshop.model.PetshopsResponse
import br.com.tairoroberto.lovedogs.petshop.model.UpdatePetShopResponse
import kotlinx.android.synthetic.main.fragment_list_petshops.*

/**
 * Created by tairo on 8/15/17.
 */
class PetshopPresenter : PetshopContract.Presenter {

    private var view: PetshopContract.View? = null
    private var model: PetshopContract.Model? = null
    override fun attachView(view: PetshopContract.View) {
        this.view = view
        this.model = PetshopModel(this)
    }

    override fun detachView() {
        this.view = null
    }

    override fun loadPetshops() {
        model?.listarPetshops()
    }

    override fun manipulatePetshopsResponse(petshopsResponse: PetshopsResponse) {
        Log.i("LOG", "petshops ${petshopsResponse.petshops}")

        if (petshopsResponse.success) {
            view?.showPetshopsList(petshopsResponse.petshops)
        }
    }

    override fun updatePetshop(petShop: PetShop?) {
        petShop?.favorite = true
        model?.updatePetshop(petShop as PetShop)
    }

    override fun manipulateUpdatePetshopResponse(updatePetShopResponse: UpdatePetShopResponse) {
        if (updatePetShopResponse.success) {
            view?.updateList(updatePetShopResponse.petShop)
        } else {
            view?.getActivity()?.showSnackBarError(view?.getActivity()?.recyclerViewPets!!, "Falha ao adicionar favorito :( ")
        }
    }

    override fun showError(str: String) {
        view?.getActivity()?.showSnackBarError(view?.getActivity()?.recyclerViewPets!!, str)
    }
}